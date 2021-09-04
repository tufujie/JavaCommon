package com.jef.mq;

import com.jef.common.context.SpringContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import java.util.Map;

/**
 * 消息生产者处理
 * @author Jef
 * @date 2019/4/1
 */
public class MessageProduceUtil {
    static Logger logger = LogManager.getLogger(MessageProduceUtil.class.getName());

    /**
     * 消息发送
     * @param adaptor 适配器
     * @param param 请求参数
     */
    public static void sendQueueMessage(final String adaptor, Map param) {
        try {
            logger.info("---------------生产者发了一个queue消息：" + adaptor + " param " + param);
            // 获取配置的目的地以及jms模板工厂
            Destination mq_queueDestination = SpringContextHolder.getBean("mq_queueDestination");
            JmsTemplate jmsTemplate = SpringContextHolder.getBean("mq_jmsTemplate");
            // 消息创建器
            ReMessageCreator creator = new ReMessageCreator(param, adaptor);
            // 设置超时
            // jmsTemplate.setReceiveTimeout(1000000);
            // 发送消息
            jmsTemplate.send(mq_queueDestination, creator);
        } catch (Exception e) {
            logger.error("error",e);
            // e.printStackTrace();
        }
    }
}