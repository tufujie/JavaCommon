package com.jef.designpattern.action.observer;

import java.util.Date;

/**
 * 摇号结果vo
 *
 * @author Jef
 * @date 2021/12/11
 */
public class LotteryResult {

    /**
     * 用户编码
     */
    private String uId;
    /**
     * 摇号结果
     */
    private String lotteryResult;
    /**
     * 摇号日期
     */
    private Date date;

    public LotteryResult(String uId, String lotteryResult, Date date) {
        this.uId = uId;
        this.lotteryResult = lotteryResult;
        this.date = date;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(String lotteryResult) {
        this.lotteryResult = lotteryResult;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}