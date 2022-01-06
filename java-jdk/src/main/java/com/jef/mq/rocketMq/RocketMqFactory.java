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
     * @return
     * @throws MQClientException
     */
    public synchronized static DefaultMQProducer getInstance() throws MQClientException {
        if (producer == null) {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            //声明并初始化一个producer
            //需要一个producer group名字作为构造方法的参数
            producer = new DefaultMQProducer(RocketMqConfiguration.PRODUCER_GROUP_NAME);
            //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
            //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
            producer.setNamesrvAddr(RocketMqConfiguration.NAMESRV_ADDR);
            //调用start()方法启动一个producer实例
            producer.start();
            stopWatch.stop();
            System.out.println("RocketMq Producer Started.");
            System.out.println("启动花费时间=" + stopWatch.getTotalTimeMillis() + "ms");
        }
        return producer;
    }

}