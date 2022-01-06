package com.jef.mq.rocketMq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * 测试RocketMq生产者进行消息生产
 *
 * @author Jef
 * @date 2021/4/6 14:35
 */
public class RocketMqProducer {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = RocketMqFactory.getInstance();
        //发送10条消息到Topic为topic_name_test，tag为tag_name_test，消息内容为“Hello RocketMQ”拼接上i的值
        for (int i = 0; i < 10; i++) {
            try {
                String messageContent = "Hello RocketMQ " + i;
                Message msg = new Message(RocketMqConfiguration.TOPIC_NAME, RocketMqConfiguration.TAGS[i % RocketMqConfiguration.TAGS.length],
                        RocketMqConfiguration.KEY_NMAE,
                        messageContent.getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                System.out.println("生产消息，消息内容=" + messageContent);
                // 这里设置需要延时的等级即可
                msg.setDelayTimeLevel(1);
                //调用producer的send()方法发送消息
                //这里调用的是同步的方式，所以会有返回结果
                int orderId = i % 10;
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    // 选择发送消息的队列
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        // arg的值其实就是orderId
                        Integer id = (Integer) arg;
                        // mqs是队列集合，也就是topic所对应的所有队列
                        int index = id % mqs.size();
                        // 这里根据前面的id对队列集合大小求余来返回所对应的队列
                        return mqs.get(index);
                    }
                }, orderId);
                //打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println(sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        //发送完消息之后，调用shutdown()方法关闭producer
//        producer.shutdown();
    }
}