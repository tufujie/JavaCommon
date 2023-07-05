package com.jef.designpattern.structure.flyweight;

/**
 * 统一查询控制管理类
 *
 * @author Jef
 * @date 2021/12/8
 */
public class ActivityController {
    private RedisUtils redisUtils = new RedisUtils();

    public Activity queryActivityInfo(Long id, Stock stock) {
        Activity activity = ActivityFactory.getActivity(id);
        // 模拟从Redis中获取库存变化信息
        stock.setUsed(redisUtils.getStockUsed());
        activity.setStock(stock);
        return activity;
    }

}