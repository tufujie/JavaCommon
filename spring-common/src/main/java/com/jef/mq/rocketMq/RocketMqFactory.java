package com.jef.mq.rocketMq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RocketMQ工厂类
 * @author Jef
 * @date: 2021/4/6 14:35
 */
/*@Slf4j
@Component*/
public class RocketMqFactory {

    /*private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DefaultMQProducer producer;
    *//**
     * DefaultMQPushConsumer和DefaultMQPullConsumer的区别是：
     * push由RocketMQ服务器主动下发消息给消费者，而pull是由消费者自己去拉取RocketMQ的消息
     * 实际上push也是由消费者定时pull，只是这个动作由client定时执行，在使用者看来就和push一样的效果
     *//*
    private DefaultMQPushConsumer consumer;

    @Autowired
    private RocketMqConfiguration configuration;

    *//**
     * 初始化消费者
     *//*
    @PostConstruct
    private void initConsumer() {
        consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(configuration.getMqAddr());
        consumer.setConsumerGroup(configuration.getConsumerGroup());
    }

    *//**
     * 初始化生产者
     *//*
    @PostConstruct
    private void initProducer() {
        producer = new DefaultMQProducer();
        producer.setNamesrvAddr(configuration.getMqAddr());
        producer.setProducerGroup(configuration.getProducerGroup());
        try {
            // 在发送消息前启动Producer
            producer.start();
            log.info("producer start success. start time:{}", sdf.format(new Date()));
        } catch (MQClientException e) {
            log.error("start producer failed. error message:{}", e.getErrorMessage());
        }
    }

    *//**
     * 发送消息
     *
     * @param topic topic
     * @param tag   tag
     * @param msg   发送的消息
     *//*
    public void sendMsg(String topic, String tag, String msg) {
        Assert.hasText(topic, "topic not blank");
        Assert.hasText(tag, "tag not blank");
        Assert.hasText(msg, "msg not blank");
        Message message = new Message(topic, tag, msg.getBytes());
        try {
            producer.send(message);
        } catch (Exception e) {
            log.error("send message failed. error message:{}", e.getMessage());
        }
    }

    *//**
     * 获取消费者
     * 例：订阅topic_1的tag_0,tag_1，topic_2的tag_2,tag_3，则入参为：
     * topic=topic_1,topic_2
     * tag=tag_0||tag_1,tag_2||tag_3
     *
     * @param topic 支持订阅多个topic，多个topic之间以","分隔
     * @param tag   支持订阅多个tag，不同topic之间的tag用","分隔，相同topic下的tag以"||"分隔
     * @return 消费者
     *//*
    public DefaultMQPushConsumer getConsumer(String topic, String tag) {
        Assert.hasText(topic, "topics not blank");
        Assert.hasText(tag, "tags not blank");
        try {
            String[] topics = topic.split(",");
            String[] tags = tag.split(",");
            if (topics.length != tags.length) {
                String errorMsg = String.format("The number of topic and tag does not match. topic:%s, tag:%s", topic, tag);
                log.warn(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
            for (int i = 0; i < topics.length; i++) {
                Assert.hasText(topics[i], "topic not blank");
                Assert.hasText(tags[i], "tag not blank");
            }
            for (int i = 0; i < topics.length; i++) {
                consumer.subscribe(topics[i], tags[i]);
                log.info("subscribe -> topic:{}, tag:{}", topics[i], tags[i]);
            }
        } catch (MQClientException e) {
            log.error("subscribe fail. topic:{}, tag:{}, error message:{}", topic, tag, e.getErrorMessage());
        }
        return consumer;
    }*/

}