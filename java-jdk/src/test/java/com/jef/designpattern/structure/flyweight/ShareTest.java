package com.jef.designpattern.structure.flyweight;

import com.jef.util.PrintUtil;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * 享元模式测试
 *
 * @author Jef
 * @date 2021/12/8
 */
public class ShareTest {

    @Test
    public void testShare() {
        ActivityController activityController = new ActivityController();
        int total = 10;
        Stock stock = new Stock(total);
        Long activityID = 1000L;
        long wantBuyTotal = total + 10;
        for (long idx = 1; idx <= wantBuyTotal; idx++) {
            Activity activity = activityController.queryActivityInfo(activityID, stock);
            System.out.printf("库存总量=%s,欲购买人数=%s,当前人次=%s, %s", total, wantBuyTotal, idx, JSON.toJSONString(activity));
            PrintUtil.changeLine();
            if (activity.getStock().getUsed() >= activity.getStock().getTotal()) {
                System.out.println("活动结束");
                break;
            }
        }
    }

}