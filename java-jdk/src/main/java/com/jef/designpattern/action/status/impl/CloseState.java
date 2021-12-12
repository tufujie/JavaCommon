package com.jef.designpattern.action.status.impl;


import com.jef.designpattern.action.status.ActivityResult;
import com.jef.designpattern.action.status.ActivityService;
import com.jef.designpattern.action.status.ActivityState;
import com.jef.designpattern.action.status.ActivityStatus;

/**
 * 活动状态；活动关闭
 */
public class CloseState extends ActivityState {

    public ActivityResult arraignment(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动关闭不可提审");
    }

    public ActivityResult checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动关闭不可审核通过");
    }

    public ActivityResult checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动关闭不可审核拒绝");
    }

    @Override
    public ActivityResult checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动关闭不可撤销审核");
    }

    public ActivityResult close(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动关闭不可重复关闭");
    }

    public ActivityResult open(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.OPEN);
        return new ActivityResult("0000", "活动开启完成");
    }

    public ActivityResult doing(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动关闭不可变更活动中");
    }

}
