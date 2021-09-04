package com.jef.mq.rocketMq;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ配置类
 *
 * @author Jef
 * @date: 2021/4/6 14:33
 */
/*@Data
@Configuration*/
public class RocketMqConfiguration {

    /**
     * RocketMQ服务端地址
     */
    private String mqAddr = "127.0.0.1:9876";
    /**
     * 消费组名称
     */
    private String consumerGroup = "consumer-test";
    /**
     * 生产组名称
     */
    private String producerGroup = "producer-test";

}