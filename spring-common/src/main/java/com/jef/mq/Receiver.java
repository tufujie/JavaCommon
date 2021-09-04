package com.jef.mq;


/**
 * mq消息消费者
 * @author Jef
 * @date 2019/3/29
 */
public class Receiver {

    public static void main(String[] args) {
        ReceiverUtil.dealReceiveMessage();
    }
}