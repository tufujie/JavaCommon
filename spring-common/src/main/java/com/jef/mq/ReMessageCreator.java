package com.jef.mq;

import com.google.gson.Gson;
import org.springframework.jms.core.MessageCreator;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Map;

/**
 * 消息创建者
 * 带有adaptor和请求参数param
 * @author Jef
 * @date 2019/4/1
 */
public class ReMessageCreator implements MessageCreator {
    private String adaptor ;
    private Map param;

    public ReMessageCreator(Map param, String adaptor){
        this.param = param;
        this.adaptor = adaptor;
    }

    @Override
    public Message createMessage(Session session) throws JMSException {
        TextMessage message = session.createTextMessage(adaptor);
        Gson gson = new Gson();
        // 装配消息
        message.setStringProperty("json", gson.toJson(this.param));
        return message;
    }

    public String getAdaptor() {
        return adaptor;
    }

    public void setAdaptor(String adaptor) {
        this.adaptor = adaptor;
    }
}