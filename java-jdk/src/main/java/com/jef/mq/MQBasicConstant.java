package com.jef.mq;

/**
 * MQ基础常量
 *
 * @author Jef
 * @date 2019/3/29
 */
public interface MQBasicConstant {
    /**
     * 地址
     */
    String MQ_URL = "tcp://localhost:61616";
    /**
     * queue名称
     */
    String MQ_QUEUE_NAME = "FirstQueue";
    /**
     * RabbitMQ host
     */
    String RABBIT_MQ_HOST = "localhost";
    /**
     * RabbitMQ host
     */
    String RABBIT_MQ_USERNAME = "guest";
    /**
     * RabbitMQ host
     */
    String RABBIT_MQ_PASSWORD = "guest";
}