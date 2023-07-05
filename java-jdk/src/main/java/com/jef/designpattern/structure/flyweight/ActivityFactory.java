package com.jef.designpattern.structure.flyweight;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 *
 * @author Jef
 * @date 2021/12/8
 */
public class ActivityFactory {

    private static Map<Long, Activity> activityMap = new HashMap<Long, Activity>();

    public static Activity getActivity(Long id) {
        Activity activity = activityMap.get(id);
        if (activity == null) {
            // 模拟从实际业务中获取活动信息
            activity = new Activity();
            activity.setId(id);
            activity.setName("双十一");
            activity.setDesc("双十一活动大促销");
            activity.setStartTime(new Date());
            activity.setStopTime(new Date());
            activityMap.put(id, activity);
        }
        return activity;
    }

}