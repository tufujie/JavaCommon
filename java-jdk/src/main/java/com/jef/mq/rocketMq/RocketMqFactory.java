package com.jef.mq.rocketMq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.util.StopWatch;

/**
 * RocketMQ工厂类
 *
 * @author Jef
 * @date 2021/9/6
 */
public class RocketMqFactory {
    private static DefaultMQProducer producer = null;

    /**
     * RocketMQ生产者单例
     * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例
     * 注意：ProducerGroupName需要由应用来保证唯一
     * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
     * 因为服务器会回查这个Group下的任意一个Producer
     *
     * @return
     * @throws MQClientException
     */
    public synchronized static DefaultMQProducer getInstance() throws MQClientException {
        if (producer == null) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            // 声明并初始化一个producer
            // 需要一个producer group名字作为构造方法的参数
            producer = new DefaultMQProducer(RocketMqConstants.PRODUCER_GROUP_NAME);
            // 设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
            // NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
            producer.setNamesrvAddr(RocketMqConstants.NAMESRV_ADDR);
            producer.setInstanceName("Producer");
            // 调用start()方法启动一个producer实例
            /**
             * Producer对象在使用之前必须要调用start初始化，初始化一次即可
             * 注意：切记不可以在每次发送消息时，都调用start方法
             */
            producer.start();
            stopWatch.stop();
            System.out.println(".");
            System.out.println("RocketMq生产者启动，启动花费时间=" + stopWatch.getTotalTimeMillis() + "ms");
        }
        return producer;
    }

}