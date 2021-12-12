package com.jef.designpattern.action.status;

import com.jef.designpattern.action.status.impl.CheckState;
import com.jef.designpattern.action.status.impl.CloseState;
import com.jef.designpattern.action.status.impl.DoingState;
import com.jef.designpattern.action.status.impl.EditingState;
import com.jef.designpattern.action.status.impl.OpenState;
import com.jef.designpattern.action.status.impl.PassState;
import com.jef.designpattern.action.status.impl.RefuseState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 状态处理服务
 *
 * @author Jef
 * @date 2021/12/12
 */
public class ActivityStateHandler {

    private Map<Enum<ActivityStatus>, ActivityState> stateMap = new ConcurrentHashMap<Enum<ActivityStatus>, ActivityState>();

    public ActivityStateHandler() {
        stateMap.put(ActivityStatus.CHECK, new CheckState());     // 待审核
        stateMap.put(ActivityStatus.CLOSE, new CloseState());     // 已关闭
        stateMap.put(ActivityStatus.DOING, new DoingState());     // 活动中
        stateMap.put(ActivityStatus.EDITING, new EditingState()); // 编辑中
        stateMap.put(ActivityStatus.OPEN, new OpenState());       // 已开启
        stateMap.put(ActivityStatus.PASS, new PassState());       // 审核通过
        stateMap.put(ActivityStatus.REFUSE, new RefuseState());   // 审核拒绝
    }

    public ActivityResult arraignment(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).arraignment(activityInfo.getActivityId(), activityInfo.getStatus());
    }

    public ActivityResult checkPass(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).checkPass(activityInfo.getActivityId(), activityInfo.getStatus());
    }

    public ActivityResult checkRefuse(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).checkRefuse(activityInfo.getActivityId(), activityInfo.getStatus());
    }

    public ActivityResult checkRevoke(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).checkRevoke(activityInfo.getActivityId(), activityInfo.getStatus());
    }

    public ActivityResult close(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).close(activityInfo.getActivityId(), activityInfo.getStatus());
    }

    public ActivityResult open(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).open(activityInfo.getActivityId(), activityInfo.getStatus());
    }

    public ActivityResult doing(ActivityInfo activityInfo) {
        return stateMap.get(activityInfo.getStatus()).doing(activityInfo.getActivityId(), activityInfo.getStatus());
    }

}
