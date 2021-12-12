package com.jef.designpattern.action.status;

import java.util.Date;

public class ActivityInfo {

    private String activityId;    // 活动ID
    private String activityName;  // 活动名称
    private Enum<ActivityStatus> status;  // 活动状态
    private Date beginTime;       // 开始时间
    private Date endTime;         // 结束时间

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Enum<ActivityStatus> getStatus() {
        return status;
    }

    public void setStatus(Enum<ActivityStatus> status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
