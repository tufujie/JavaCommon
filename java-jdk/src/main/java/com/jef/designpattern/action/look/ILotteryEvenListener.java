package com.jef.designpattern.action.look;

/**
 * 事件监听
 *
 * @author Jef
 * @date 2021/12/11
 */
public interface ILotteryEvenListener {

    /**
     * 摇号结果监听
     *
     * @param lotteryResult 摇号vo
     * @author Jef
     * @date 2021/12/11
     */
    void doEvent(LotteryResult lotteryResult);
}