package com.jef.mq.rocketMq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * RocketMQ工厂类
 * @author Jef
 * @date 2021/4/6 14:35
 */
public class RocketMqProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        //声明并初始化一个producer
        //需要一个producer group名字作为构造方法的参数
        DefaultMQProducer producer = new DefaultMQProducer(RocketMqConfiguration.PRODUCER_GROUP_NAME);
        //设置NameServer地址,此处应改为实际NameServer地址，多个地址之间用；分隔
        //NameServer的地址必须有，但是也可以通过环境变量的方式设置，不一定非得写死在代码里
        producer.setNamesrvAddr(RocketMqConfiguration.NAMESRV_ADDR);
        //调用start()方法启动一个producer实例
        producer.start();
        System.out.println("RocketMq Producer Started.");
        //发送10条消息到Topic为topic_name_test，tag为tag_name_test，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 10; i++) {
            try {
                Message msg = new Message(RocketMqConfiguration.TOPIC_NAME,
                       RocketMqConfiguration.TAG_NMAE,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                //调用producer的send()方法发送消息
                //这里调用的是同步的方式，所以会有返回结果
                SendResult sendResult = producer.send(msg);
                //打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        //发送完消息之后，调用shutdown()方法关闭producer
        producer.shutdown();
    }
}