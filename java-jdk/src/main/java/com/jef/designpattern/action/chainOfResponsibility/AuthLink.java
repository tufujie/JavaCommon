package com.jef.designpattern.action.chainOfResponsibility;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 链路抽象类定义
 * 所有审批都要三级审批人审批、6月1号到6月25号之间的需要二级审批人审批、6月11号到6月20号的审批需要一级审批人审批
 *
 * @author Jef
 * @date 2021/12/11
 */
public abstract class AuthLink {

    // 时间格式化
    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected String levelUserId; // 级别人员ID
    protected String levelUserName; // 级别人员姓名
    /**
     * 节点，从n到0，0表示最终节点，适合于固定审批人的审批流
     */
    private int node;

    private AuthLink next; // 责任链

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink(String levelUserId, String levelUserName, int node) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
        this.node = node;
    }

    public AuthLink next() {
        return next;
    }

    public AuthLink appendNext(AuthLink next) {
        this.next = next;
        return this;
    }

    public abstract void doAuth(String uId, String orderId, Date authDate);


    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }
}