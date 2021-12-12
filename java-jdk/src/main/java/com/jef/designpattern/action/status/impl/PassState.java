package com.jef.designpattern.action.status.impl;

import com.jef.designpattern.action.status.ActivityResult;
import com.jef.designpattern.action.status.ActivityService;
import com.jef.designpattern.action.status.ActivityState;
import com.jef.designpattern.action.status.ActivityStatus;

/**
 * 活动状态；审核通过
 */
public class PassState extends ActivityState {

    public ActivityResult arraignment(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "已审核状态不可重复提审");
    }

    public ActivityResult checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "已审核状态不可重复审核");
    }

    public ActivityResult checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.REFUSE);
        return new ActivityResult("0000", "活动审核拒绝完成");
    }

    @Override
    public ActivityResult checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "审核通过不可撤销(可先拒绝审核)");
    }

    public ActivityResult close(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.CLOSE);
        return new ActivityResult("0000", "活动审核关闭完成");
    }

    public ActivityResult open(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "非关闭活动不可开启");
    }

    public ActivityResult doing(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.DOING);
        return new ActivityResult("0000", "活动变更活动中完成");
    }

}
