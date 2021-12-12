package com.jef.designpattern.action.status;

import com.jef.util.PrintUtil;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * 状态模式测试
 *
 * @author Jef
 * @date 2021/12/12
 */
public class StatusTest {

    @Test
    public void testEditing2Arraignment() {
        String activityId = "100001";
        ActivityInfo activityInfo = ActivityService.init(activityId, ActivityStatus.EDITING);
        ActivityStateHandler stateHandler = new ActivityStateHandler();
        ActivityResult result = stateHandler.arraignment(activityInfo);
        PrintUtil.printf("编辑中To提审活动：%s", JSON.toJSONString(result));
        PrintUtil.printf("活动当前信息：%s，状态：%s",
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()));
    }

    @Test
    public void testEditing2Open() {
        String activityId = "100001";
        ActivityInfo activityInfo = ActivityService.init(activityId, ActivityStatus.EDITING);
        ActivityStateHandler stateHandler = new ActivityStateHandler();
        ActivityResult result = stateHandler.open(activityInfo);
        PrintUtil.printf("编辑中To开启活动：%s", JSON.toJSONString(result));
        PrintUtil.printf("活动当前信息：%s，状态：%s",
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
                ));
    }

    @Test
    public void testCheck2Pass() {
        String activityId = "100001";
        ActivityInfo activityInfo = ActivityService.init(activityId, ActivityStatus.CHECK);
        ActivityStateHandler stateHandler = new ActivityStateHandler();
        ActivityResult result = stateHandler.checkPass(activityInfo);
        PrintUtil.printf("待审核To审核通过：%s", JSON.toJSONString(result));
        PrintUtil.printf("活动当前信息：%s，状态：%s",
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
                ));
    }

    @Test
    public void testCheck2Refuse() {
        String activityId = "100001";
        ActivityInfo activityInfo = ActivityService.init(activityId, ActivityStatus.CHECK);
        ActivityStateHandler stateHandler = new ActivityStateHandler();
        ActivityResult result = stateHandler.checkRefuse(activityInfo);
        PrintUtil.printf("待审核To审核拒绝：%s", JSON.toJSONString(result));
        PrintUtil.printf("活动当前信息：%s，状态：%s",
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
                ));
    }

    @Test
    public void testRefuse2Doing() {
        String activityId = "100001";
        ActivityInfo activityInfo = ActivityService.init(activityId, ActivityStatus.REFUSE);
        ActivityStateHandler stateHandler = new ActivityStateHandler();
        ActivityResult result = stateHandler.doing(activityInfo);
        PrintUtil.printf("拒绝To活动中：%s", JSON.toJSONString(result));
        PrintUtil.printf("活动当前信息：%s，状态：%s",
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
                ));
    }

    @Test
    public void testRefuse2Revoke() {
        String activityId = "100001";
        ActivityInfo activityInfo = ActivityService.init(activityId, ActivityStatus.REFUSE);
        ActivityStateHandler stateHandler = new ActivityStateHandler();
        ActivityResult result = stateHandler.checkRevoke(activityInfo);
        PrintUtil.printf("拒绝To撤审：%s", JSON.toJSONString(result));
        PrintUtil.printf("活动当前信息：%s，状态：%s",
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
                JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()
                ));
    }

}