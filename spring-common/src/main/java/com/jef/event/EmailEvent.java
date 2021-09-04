package com.jef.event;

import org.springframework.context.ApplicationEvent;

/**
 * Spring事件机制，邮箱事件
 */
public class EmailEvent extends ApplicationEvent {
    private String address; // 邮箱

    private String title; // 标题

    private String content; // 内容

    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(Object source, String address, String title, String content) {
        super(source);
        this.address = address;
        this.title = title;
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
