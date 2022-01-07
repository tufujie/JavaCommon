package com.jef.mq.activeMq;

import com.jef.entity.User;
import com.jef.mq.MQBasicConstant;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.io.Serializable;

/**
 * mq消息接收
 * @author Jef
 * @date 2019/3/29
 */
public class ReceiverUtil {

    public static void dealReceiveMessage() {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // 消费者，消息接收者
        MessageConsumer consumer;
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD, MQBasicConstant.MQ_URL);
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.FALSE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值FirstQueue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue(MQBasicConstant.MQ_QUEUE_NAME);
            consumer = session.createConsumer(destination);
            while (true) {
                // 设置接收者接收消息的时间，为了便于测试，这里写定为100s
                ObjectMessage message = (ObjectMessage) consumer.receive(100000);
                if (null != message) {
                    Serializable serializable = message.getObject();
                    if (serializable instanceof User) {
                        User user = (User) serializable;
                        System.out.println("接收到用户信息，用户名称为：" + user.getName());
                    }
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (Throwable ignore) {
            }
        }
    }
}