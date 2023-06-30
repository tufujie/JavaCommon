package com.jef.designpattern.action.observer;

/**
 * 摇号服务接口
 *
 * @author Jef
 * @date 2021/12/11
 */
public abstract class LotteryService {
    private LotteryEventManager lotteryEventManager;

    public LotteryService() {
        lotteryEventManager = new LotteryEventManager(LotteryEventManager.LotteryEventType.MQ, LotteryEventManager.LotteryEventType.MESSAGE);
        // 这里原理就是：就是Map映射，通过类型找出执行类，执行对应的接口方法
        lotteryEventManager.subscribe(LotteryEventManager.LotteryEventType.MQ, new LotteryMQEventListener());
        lotteryEventManager.subscribe(LotteryEventManager.LotteryEventType.MESSAGE, new LotteryMessageEventListener());
    }

    /**
     * 摇号
     *
     * @param uId 用户编码
     * @return
     */
    public LotteryResult draw(String uId) {
        LotteryResult lotteryResult = doDraw(uId);
        // 需要什么通知就给调用什么方法
        lotteryEventManager.notify(LotteryEventManager.LotteryEventType.MQ, lotteryResult);
        lotteryEventManager.notify(LotteryEventManager.LotteryEventType.MESSAGE, lotteryResult);
        return lotteryResult;
    }

    /**
     * 摇号动作
     *
     * @param uId 用户编码
     * @return com.jef.designpattern.action.look.LotteryResult
     * @author Jef
     * @date 2021/12/11
     */
    protected abstract LotteryResult doDraw(String uId);
}
