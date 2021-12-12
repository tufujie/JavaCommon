package com.jef.designpattern.action.status.impl;

import com.jef.designpattern.action.status.ActivityResult;
import com.jef.designpattern.action.status.ActivityService;
import com.jef.designpattern.action.status.ActivityState;
import com.jef.designpattern.action.status.ActivityStatus;

/**
 * 活动状态；编辑中
 */
public class EditingState extends ActivityState {

    public ActivityResult arraignment(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.CHECK);
        return new ActivityResult("0000", "活动提审成功");
    }

    public ActivityResult checkPass(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "编辑中不可审核通过");
    }

    public ActivityResult checkRefuse(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "编辑中不可审核拒绝");
    }

    @Override
    public ActivityResult checkRevoke(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "编辑中不可撤销审核");
    }

    public ActivityResult close(String activityId, Enum<ActivityStatus> currentStatus) {
        ActivityService.execStatus(activityId, currentStatus, ActivityStatus.CLOSE);
        return new ActivityResult("0000", "活动关闭成功");
    }

    public ActivityResult open(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "非关闭活动不可开启");
    }

    public ActivityResult doing(String activityId, Enum<ActivityStatus> currentStatus) {
        return new ActivityResult("0001", "编辑中活动不可执行活动中变更");
    }

}
