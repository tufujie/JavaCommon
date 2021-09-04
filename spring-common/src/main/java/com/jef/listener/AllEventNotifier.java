package com.jef.listener;

import com.jef.event.EmailEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 对所有事件的监听
 */
public class AllEventNotifier implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof EmailEvent) {
            EmailEvent emailEvent = (EmailEvent) applicationEvent;
            System.out.println("发送地址=" + emailEvent.getAddress());
            System.out.println("发送主题=" + emailEvent.getTitle());
            System.out.println("发送内容=" + emailEvent.getContent());
        } else {
            System.out.println("其他事件");
        }
    }
}
