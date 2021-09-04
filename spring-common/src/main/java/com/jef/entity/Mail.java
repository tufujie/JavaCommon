package com.jef.entity;

import java.io.Serializable;

/**
 * 邮件实体
 * 包括来源，去向，标题，内容等
 * @author Jef
 * @date 20180729
 */
public class Mail implements Serializable {

    private static final long serialVersionUID = -6311338042876777679L;

    private String from;
    private String to;
    private String title;
    private String content;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
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
