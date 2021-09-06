package com.jef.mq.rocketMq;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ配置类
 *
 * @author Jef
 * @date: 2021/4/6 14:33
 */
public interface RocketMqConfiguration {

    /**
     * RocketMQ服务端地址
     */
    String NAMESRV_ADDR = "127.0.0.1:9876";
    /**
     * 消费组名称
     */
    String COMSUMER_GROU_NAME = "consumer_group_name_test";
    /**
     * 生产组名称
     */
    String PRODUCER_GROUP_NAME = "producer_group_name_test";
    /**
     * 主题topic
     */
    String TOPIC_NAME = "topic_name_test";
    /**
     * 二级主题tag
     */
    String TAG_NMAE = "tag_name_test";

}