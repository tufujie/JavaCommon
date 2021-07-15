package com.jef.enumkind;

import com.jef.util.LogicUtils;

/**
 * 枚举出处理的相应状态
 * @author Jef
 * @date 2019/9/8
 */
public enum ProcessStatusEnum {
    /**
     * 第一个状态
     */
    ONE_STATUS(1, "第一个状态", 3),
    /**
     * 第二个状态
     */
    TWO_STATUS(2, "第二个状态", 4);
    /**
     * 第三方状态
     */
    private int statusNum;
    /**
     * 状态描述
     */
    private String statusDescription;
    /**
     * 存储在数据库中状态
     */
    private int statusNumDb;

    ProcessStatusEnum(int statusNum, String statusDescription, int statusNumDd) {
        this.statusNum = statusNum;
        this.statusDescription = statusDescription;
        this.statusNumDb = statusNumDd;
    }

    public int getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(int statusNum) {
        this.statusNum = statusNum;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public int getStatusNumDb() {
        return statusNumDb;
    }

    public void setStatusNumDb(int statusNumDb) {
        this.statusNumDb = statusNumDb;
    }

    /**
     * 通过状态值获取对应的状态
     * @param statusNum 这里使用Integer，允许放入空值进来
     * @return
     */
    public static ProcessStatusEnum getByStatusNum(Integer statusNum) {
        // 1：要么给出一个异常，要么给一个默认的
        // 这里根据业务，没有就给个默认的，表示status在业务上不允许为空，进行为空，有两种情况，一种是没控制好，放了null进入数据库，另外一种是没有从数据库中取，只是传了null进来
        // 如果根据业务，允许status default null的情况，那边，这里两个返回默认的就应该返回空（后续判断为空的时候的处理）
        if (LogicUtils.isNull(statusNum)) {
            return ProcessStatusEnum.ONE_STATUS;
        }
        for (ProcessStatusEnum processStatusEnum : ProcessStatusEnum.values()) {
            if (processStatusEnum.getStatusNum() == statusNum) {
                return processStatusEnum;
            }
        }
        // 这里意味着status不为空，说明传进来的不在枚举中，要么状态是以前不存在的，要么就是传错了，这里可以打出日志，以便在枚举中添加，其它从处理方案同1
        return ProcessStatusEnum.ONE_STATUS;
    }

    public static void main(String[] args) {
        int statusNnum = 1;
        System.out.println(getByStatusNum(statusNnum).getStatusNumDb());
    }

}