package com.jef.test;

import com.jef.common.utils.ContextUtils;
import com.jef.constant.BasicConstant;
import com.jef.event.EmailEvent;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

public class EventListenerTest {
    @Test
    public void eventListenseTest() {
        ApplicationContext applicationContext = ContextUtils.getContextFromBeansXML();
        EmailEvent emailEvent = new EmailEvent("test", BasicConstant.EMAIL, BasicConstant.TITLE, BasicConstant.CONTENT);
        applicationContext.publishEvent(emailEvent);
    }
}
