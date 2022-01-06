package com.jef.mq.rabbitMq;

import com.jef.mq.MQBasicConstant;

import java.io.IOException;
import java.util.HashMap;

/**
 * 客户端
 *
 * @author Jef
 * @date 2020/4/4
 */
public class RabbitMQClient {
    public static void main(String[] args) {
        new RabbitMQClient();
    }

    public RabbitMQClient() {
        try {
            // 发消息
            publishMessage();
            // 注册消费者
            addConsumer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void publishMessage() throws IOException {
        RabbitMQConnectionUtil connectionUtil = new RabbitMQConnectionUtil(MQBasicConstant.MQ_QUEUE_NAME);
        MessageProducer producer = new MessageProducer(connectionUtil);
        connectionUtil.channel.confirmSelect();
        // 注意：返回的时候Return在前，Confirm在后
        connectionUtil.channel.addConfirmListener(new MyConfirmListener());
        connectionUtil.channel.addReturnListener(new MyReturnListener());
        int i = 1;
        while (i <= 10) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("tagId", i);
            producer.sendMessage(map);
            i++;
        }
    }

    public void addConsumer() throws IOException {
        RabbitMQConnectionUtil connectionUtil = new RabbitMQConnectionUtil(MQBasicConstant.MQ_QUEUE_NAME);
        OddConsumer odd = new OddConsumer(connectionUtil);
        odd.basicConsume();
        EvenConsumer even = new EvenConsumer(connectionUtil);
        even.basicConsume();
    }

}