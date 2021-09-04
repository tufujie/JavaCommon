package com.jef.mq.rocketMq.test;
import com.jef.mq.rocketMq.RocketMqFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
/**
 * 消费者测试类
 * @author Jef
 * @date 2021/4/6 14:38
 */
/*@Slf4j
@Configuration*/
public class ConsumerTest {
    /*@Autowired
    private RocketMqFactory mq;
    private String topic = "test_topic_0,test_topic_1";
    private String tag = "test_tag_0||test_tag_1,test_tag_3";

    *//**
     * 订阅消息，在程序启动时订阅
     *//*
    @PostConstruct
    private void listenMessage() {
        System.out.println("listen rocketMq msg");
        DefaultMQPushConsumer mqConsumer = mq.getConsumer(topic, tag);
        mqConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                if (list == null || list.isEmpty()) {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                MessageExt ext = list.get(0);
                if (ext == null || ext.getBody() == null || ext.getBody().length == 0) {
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                // 消费消息;
                consumeMsg(new String(ext.getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            mqConsumer.start();
        } catch (MQClientException e) {
            log.error("订阅topic失败. msg:{}", e.getErrorMessage());
        }
    }

    *//**
     * 消费消息
     *
     * @param msg 待消费的消息
     *//*
    private void consumeMsg(String msg) {
        System.out.println("消费消息:" + msg);
    }*/
}