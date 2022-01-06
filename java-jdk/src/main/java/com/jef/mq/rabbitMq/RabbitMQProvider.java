package com.jef.mq.rabbitMq;


import com.jef.constant.BasicConstant;
import com.jef.mq.MQBasicConstant;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * rabbitMQ生产者
 *
 * @author Jef
 * @date 2020/4/4
 */
public class RabbitMQProvider {
    private Logger logger = LogManager.getLogger(this.getClass());
    /**是否启用RabbitMQ*/
    /**
     * 交换器名
     */
    private static final String EXCHANE_NAME = "amq.topic";
    /**
     * 下行队列名
     */
    private static final String DOWN_QUEUE_NAME = "FirstQueue";
    private static final String DOWN_ROUTING_KEY = "FirstQueue";
    private static final String UP_QUEQU_NAME = "FirstQueue";
    private static final String UP_ROUTING_KEY = "FirstQueue";
    private static final Integer PORT = 15672;
    // 是否自动应答
    private static final boolean AUTO_ACK = false;
    private Channel channelDown;
    private Channel channelUp;
    private Connection upConnection;
    private Connection downConnection;
    private static final int MESSAGE_LIFE_TIME = 100;

    /**
     * 处理接收到的消息的处理实例，即我们的业务代码
     */

    public static void main(String[] args) {
        new RabbitMQProvider().init();
    }

    /**
     * 初始化
     *
     * @param
     * @return void
     * @author Jef
     * @date 2020/4/4
     */
    @PostConstruct
    public void init() {
        try {
            // 创建连接
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(MQBasicConstant.RABBIT_MQ_USERNAME);
            factory.setPassword(MQBasicConstant.RABBIT_MQ_PASSWORD);
            factory.setHost(MQBasicConstant.RABBIT_MQ_HOST);
            factory.setPort(AMQP.PROTOCOL.PORT);
           /* // 队列参数
            Map<String, Object> args = new HashMap<>();
            // 消息过期时间
            args.put("x-message-ttl", MESSAGE_LIFE_TIME * 1000);*/

            // 创建上行连接
            upConnection = factory.newConnection();
            // 创建上行通道
            channelUp = upConnection.createChannel();
            // 声明创建配置上行队列
            channelUp.queueDeclare(UP_QUEQU_NAME, true, false, false, null);
            // 将队列与交换器绑定，并设置路由码
            channelUp.queueBind(UP_QUEQU_NAME, EXCHANE_NAME, UP_ROUTING_KEY);

            downConnection = factory.newConnection();
            channelDown = downConnection.createChannel();
            channelDown.queueDeclare(DOWN_QUEUE_NAME, true, false, false, null);
            channelDown.queueBind(DOWN_QUEUE_NAME, EXCHANE_NAME, DOWN_ROUTING_KEY);
            for (int i = 0; i < 10; i++) {
                sendMessage(BasicConstant.USER_NAME + i);
            }
            receiveMessage();
        } catch (Exception e) {
            System.out.println("启动MQ下行通道时出现异常！" + e.toString());
        }
    }

    /**
     * 持续监听队列以接收数据
     *
     * @throws IOException
     * @throws TimeoutException
     */
    private void receiveMessage() throws IOException {
        // 每次缓存5个消息在本地
        channelDown.basicQos(5);
        channelDown.basicConsume(DOWN_QUEUE_NAME, AUTO_ACK, new DefaultConsumer(channelDown) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                // 处理接收到的消息
                System.out.println(DOWN_QUEUE_NAME + " Received '" + message + "'" + ", routingKey: " + envelope.getRoutingKey());
                // 持续监听
                channelDown.basicConsume(DOWN_QUEUE_NAME, AUTO_ACK, this);
                channelDown.basicAck(envelope.getDeliveryTag(), true);
            }
        });
    }

    /**
     * 向上行消息队列发送一条消息
     *
     * @param message
     * @throws IOException
     * @throws TimeoutException
     */
    public void sendMessage(String message) throws IOException {
        channelUp.basicPublish("", UP_QUEQU_NAME, true, MessageProperties.TEXT_PLAIN, message.getBytes());
        System.out.println("send message to " + UP_QUEQU_NAME + ": " + message);
    }

}