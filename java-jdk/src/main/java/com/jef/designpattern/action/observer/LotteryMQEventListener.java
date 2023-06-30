package com.jef.designpattern.action.observer;

import com.jef.util.PrintUtil;

/**
 * MQ事件监听
 *
 * @author Jef
 * @date 2021/12/11
 */
public class LotteryMQEventListener implements ILotteryEvenListener {


    @Override
    public void doEvent(LotteryResult lotteryResult) {
        PrintUtil.printf("记录用户：%s 摇号结果（MQ）：%s", lotteryResult.getuId(), lotteryResult.getLotteryResult());
    }
}