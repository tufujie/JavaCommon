package com.jef.designpattern.action.observer;

import com.jef.util.PrintUtil;

/**
 * 短信事件监听
 *
 * @author Jef
 * @date 2021/12/11
 */
public class LotteryMessageEventListener implements ILotteryEvenListener {


    @Override
    public void doEvent(LotteryResult lotteryResult) {
        PrintUtil.printf("给用户：%s，发送短信（短信）：%s", lotteryResult.getuId(), lotteryResult.getLotteryResult());
    }
}