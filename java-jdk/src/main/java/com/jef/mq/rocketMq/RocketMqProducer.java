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

    public static void main(String[] args) throws MQClientException, InterruptedException, com.alibaba.rocketmq.client.exception.MQClientException {
        DefaultMQProducer producer = RocketMqFactory.getInstance();
        //
        /**
         * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
         * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，
         * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，
         * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
         * 发送10条消息到Topic为topic_name_jef，tag为tag_name_jef，消息内容为“Hello RocketMQ”拼接上i的值
         */
        for (int i = 0; i < 10; i++) {
            try {
                String messageContent = "Hello RocketMQ " + i;
                // 指定将消息发送到 自定义的topic 中
                Message msg = new Message(RocketMqConstants.TOPIC_NAME, RocketMqConstants.TAGS[i % RocketMqConstants.TAGS.length],
                        RocketMqConstants.KEY_NMAE,
                        messageContent.getBytes(RemotingHelper.DEFAULT_CHARSET)
                );
                System.out.println("生产消息，消息内容=" + messageContent);
                // 这里设置需要延时的等级即可
                msg.setDelayTimeLevel(1);
                // 调用producer的send()方法发送消息
                // 这里调用的是同步的方式，所以会有返回结果
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
                // 打印返回结果，可以看到消息发送的状态以及一些相关信息
                System.out.println("发送消息结果=" + sendResult);

                {
                    messageContent = "Hello RocketMQ TopicA TagA";
                    msg = new Message(RocketMqConstants.TOPIC_NAME_CUSTOM_V2, RocketMqConstants.TAG_NMAE,
                            RocketMqConstants.KEY_NMAE, messageContent.getBytes());
                    System.out.println("生产消息，消息内容=" + messageContent);
                    SendResult sendResultTwo = producer.send(msg);
                    System.out.println("发送消息结果=" + sendResultTwo);
                }
                // 相同topic 不同tag
                {
                    messageContent = "Hello RocketMQ TopicA TagB";
                    msg = new Message(RocketMqConstants.TOPIC_NAME_CUSTOM_V2, RocketMqConstants.TAG_NMAE_CUSTOM_V2,
                            RocketMqConstants.KEY_NMAE, messageContent.getBytes());
                    System.out.println("生产消息，消息内容=" + messageContent);
                    SendResult sendResultTwo = producer.send(msg);
                    System.out.println("发送消息结果=" + sendResultTwo);
                }
                // 不同topic 相同tag
                {
                    messageContent = "Hello RocketMQ TopicB TagB";
                    msg = new Message(RocketMqConstants.TOPIC_NAME_CUSTOM_V3, RocketMqConstants.TAG_NMAE_CUSTOM_V2,
                            RocketMqConstants.KEY_NMAE, messageContent.getBytes());
                    System.out.println("生产消息，消息内容=" + messageContent);
                    SendResult sendResultTwo = producer.send(msg);
                    System.out.println("发送消息结果=" + sendResultTwo);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        /**
         * 发送完消息之后，调用shutdown()方法关闭producer
         * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
         * 注意：我们建议应用在JBOSS、Tomcat等容器的退出钩子里调用shutdown方法
         */
//        producer.shutdown();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
            }
        }));
        System.exit(0);
    }
}