package com.jef.designpattern.action.status.impl;

import com.jef.designpattern.action.status.ActivityResult;
import com.jef.designpattern.action.status.ActivityService;
import com.jef.designpattern.action.status.ActivityState;
import com.jef.designpattern.action.status.ActivityStatus;

/**
 * 活动状态；活动开启
 */
public class OpenState extends ActivityState {

    public ActivityResult arraignment(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动开启不可提审");
    }

    public ActivityResult checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动开启不可审核通过");
    }

    public ActivityResult checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动开启不可审核拒绝");
    }

    @Override
    public ActivityResult checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动开启不可撤销审核");
    }

    public ActivityResult close(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.CLOSE);
        return new ActivityResult("0000", "活动关闭完成");
    }

    public ActivityResult open(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "活动不可重复开启");
    }

    public ActivityResult doing(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.DOING);
        return new ActivityResult("0000", "活动变更活动中完成");
    }

}