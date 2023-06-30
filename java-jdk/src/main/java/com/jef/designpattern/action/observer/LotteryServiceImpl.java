package com.jef.designpattern.action.observer;

import java.util.Date;

/**
 * 摇号服务实现
 *
 * @author Jef
 * @date 2021/12/11
 */
public class LotteryServiceImpl extends LotteryService {
    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    /**
     * 摇号动作
     * 实际的业务
     *
     * @param uId 用户编码
     * @return com.jef.designpattern.action.look.LotteryResult
     * @author Jef
     * @date 2021/12/11
     */
    public LotteryResult doDraw(String uId) {
        // 摇号
        String lotteryResult = minibusTargetService.getLotteryResult(uId);
        // 结果
        return new LotteryResult(uId, lotteryResult, new Date());
    }
}