package com.jef.mq.rocketMq;

/**
 * RocketMQ配置类
 *
 * @author Jef
 * @date: 2021/4/6 14:33
 */
public interface RocketMqConstants {

    /**
     * RocketMQ服务端地址
     */
    String NAMESRV_ADDR = "127.0.0.1:9876";
    /**
     * 消费组名称
     */
    String COMSUMER_GROU_NAME = "consumer_group_name_jef";
    /**
     * 生产组名称
     */
    String PRODUCER_GROUP_NAME = "producer_group_name_jef";
    /**
     * 主题topic
     */
    String TOPIC_NAME = "topic_name_jef";
    /**
     * 主题topic
     */
    String TOPIC_NAME_CUSTOM_V2 = "topic_name_jef_v2";
    /**
     * 主题topic
     */
    String TOPIC_NAME_CUSTOM_V3 = "topic_name_jef_v3";
    /**
     * 二级主题tag
     */
    String TAG_NMAE = "tag_name_jef";
    /**
     * 二级主题tag
     */
    String TAG_NMAE_CUSTOM_V2 = "tag_name_jef_V2";
    /**
     * 二级主题tag
     */
    String KEY_NMAE = "key_name_jef";

    // 自定义一个tag数组
    String[] TAGS = new String[]{"tag_name_jefA", "tag_name_jefB", "tag_name_jefC", "tag_name_jefD", "tag_name_jefE"};

}