package com.jef.mq.rocketMq.test;

import com.jef.mq.rocketMq.RocketMqFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 生产者测试类
 */
//@Configuration
public class ProducerTest {
    /*private static final String TOPIC_PRE = "test_topic_";
    private static final String TAG_PRE = "test_tag_";
    private static int index = 0;

    @Autowired
    private RocketMqFactory mq;

    *//**
     * @Schedule需要生效的话，需要在启动类加上@EnableScheduling注解
     *//*
    @Scheduled(cron = "0/5 * * * * ?")
    private void sendMsg() {
        String topic = TOPIC_PRE + (index / 2);
        String tag = TAG_PRE + (index);
        String msg = topic + " -> " + tag;
        mq.sendMsg(topic, tag, msg);
        System.out.println("        发送消息:" + msg);
        if (++index >= 4) {
            index = 0;
        }
    }*/
}