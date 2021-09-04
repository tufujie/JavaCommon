package com.jef.mq;

import com.google.gson.Gson;
import com.jef.common.context.SpringContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 消费者队列监听器
 * @author Jef
 * @date 2019/4/1
 */
public class ReMessageListener implements MessageListener {
    public static final Logger logger = LogManager.getLogger(ReMessageListener.class.getName());

    @Override
    public void onMessage(Message message) {
        String adaptor;
        String json;
        try {
            TextMessage textMsg = (TextMessage) message;
            // 获取Bean和方法名
            adaptor = textMsg.getText();
            int index = adaptor.lastIndexOf(".");

            String beanName = adaptor.substring(0, index);
            String methodName = adaptor.substring(index + 1);

            // 2. 找到适配器处理消息
            Object object = SpringContextHolder.getBean(beanName);

            Gson gson = new Gson();
            // json中存放了请求参数，拿出来解析
            json = message.getStringProperty("json");
            Map param = gson.fromJson(json, HashMap.class);

            // 反射调用消息处理方法
            Class clazz = object.getClass();
            Method method = clazz.getDeclaredMethod(methodName, Map.class);
            method.invoke(object, param);
        } catch (Exception e2) {
            logger.error("error", e2);
        }
    }
}