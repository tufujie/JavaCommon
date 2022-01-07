package com.jef.mq.rocketMq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 测试RocketMq消费者消息消息
 *
 * @author Jef
 * @date 2021/9/6
 */
public class RocketMqConsumer {

    /**
     * 当前例子是RocketMqConsumer用法，使用方式给用户感觉是消息从RocketMQ服务器推到了应用客户端。
     * 但是实际RocketMqConsumer内部是使用长轮询Pull方式从MetaQ服务器拉消息，然后再回调用户Listener方法
     */
    public static void main(String[] args) throws MQClientException {
        /**
         * 声明并初始化一个consumer
         * 使用自定义的消费者分组，需要一个consumer group名字作为构造方法的参数
         * 一个应用创建一个Consumer，由应用来维护此对象，可以设置为全局对象或者单例
         * 注意：ConsumerGroupName需要由应用来保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMqConstants.COMSUMER_GROU_NAME);
        // 同样也要设置NameServer地址
        consumer.setNamesrvAddr(RocketMqConstants.NAMESRV_ADDR);
        // 这里设置的是一个consumer的消费策略
        // CONSUME_FROM_LAST_OFFSET 默认策略，从该队列最尾开始消费，即跳过历史消息
        // CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还储存在broker的）全部消费一遍
        // CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和setConsumeTimestamp()配合使用，默认是半个小时以前
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /**
         * 订阅指定topic下所有消息
         * 注意：一个consumer对象可以订阅多个topic
         */
        consumer.subscribe(RocketMqConstants.TOPIC_NAME, "*");
        /**
         * 订阅指定topic下tags分别等于TagA或TagB
         * 设置consumer所订阅的Topic和Tag，*代表全部的Tag，订阅 自定义的topic
         */
        consumer.subscribe(RocketMqConstants.TOPIC_NAME_CUSTOM_V2, RocketMqConstants.TAG_NMAE + "||" + RocketMqConstants.TAG_NMAE_CUSTOM_V2);
        consumer.subscribe(RocketMqConstants.TOPIC_NAME_CUSTOM_V3, "*");
        // 设置一个Listener，主要进行消息的逻辑处理
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                System.out.println("线程 " + Thread.currentThread().getName() + " 接收到新消息条数: " + msgs.size());

                MessageExt msg = msgs.get(0);
                // 不同topic不同tag可以走不同的消费逻辑
                if (RocketMqConstants.TOPIC_NAME.equals(msg.getTopic())) {
                    System.out.println("打印消息内容版本1，tag=" + msg.getTags() + "；消息=" + new String(msg.getBody()));
                } else if (RocketMqConstants.TOPIC_NAME_CUSTOM_V2.equals(msg.getTopic())) {
                    // 执行 自定义的topic 的消费逻辑
                    if (msg.getTags() != null && RocketMqConstants.TAG_NMAE.equals(msg.getTags())) {
                        // 执行 TagA 的消费
                        System.out.println("打印消息内容版本2，tag=" + msg.getTags() + "；消息=" + new String(msg.getBody()));
                    } else if (msg.getTags() != null && RocketMqConstants.TAG_NMAE_CUSTOM_V2.equals(msg.getTags())) {
                        // 执行 TagB 的消费
                        System.out.println("打印消息内容版本3，tag=" + msg.getTags() + "；消息=" + new String(msg.getBody()));
                    }

                } else if (RocketMqConstants.TOPIC_NAME_CUSTOM_V3.equals(msg.getTopic())) {
                    System.out.println("打印消息内容版本4，tag=" + msg.getTags() + "；消息=" + new String(msg.getBody()));
                }
                // 返回消费状态
                // CONSUME_SUCCESS 消费成功
                // RECONSUME_LATER 消费失败，需要稍后重新消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 调用start()方法启动consumer
        /**
         * Consumer对象在使用之前必须要调用start初始化，初始化一次即可
         */
        consumer.start();
        System.out.println("RocketMq消费者启动");
    }
}