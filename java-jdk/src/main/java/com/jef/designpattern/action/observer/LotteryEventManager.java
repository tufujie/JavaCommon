package com.jef.designpattern.action.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 观察者模式-摇号事件管理
 *
 * @author Jef
 * @date 2021/12/11
 */
public class LotteryEventManager {
    /**
     * 所有的监听者
     */
    Map<Enum<LotteryEventType>, List<ILotteryEvenListener>> listenersMap = new HashMap<>();

    /**
     * 消息类型美剧
     */
    public enum LotteryEventType {
        MQ, MESSAGE
    }

    /**
     * 初始化通知类别
     *
     * @param operations
     * @author Jef
     * @date 2021/12/11
     */
    public LotteryEventManager(Enum<LotteryEventType>... operations) {
        for (Enum<LotteryEventType> operation : operations) {
            this.listenersMap.put(operation, new ArrayList<>());
        }
    }

    /**
     * 订阅
     *
     * @param eventType 事件类型
     * @param listener  监听
     */
    public void subscribe(Enum<LotteryEventType> eventType, ILotteryEvenListener listener) {
        List<ILotteryEvenListener> listeners = listenersMap.get(eventType);
        listeners.add(listener);
    }

    /**
     * 取消订阅
     *
     * @param eventType 事件类型
     * @param listener  监听
     */
    public void unsubscribe(Enum<LotteryEventType> eventType, ILotteryEvenListener listener) {
        List<ILotteryEvenListener> listeners = listenersMap.get(eventType);
        listeners.remove(listener);
    }

    /**
     * 通知
     *
     * @param eventType 事件类型
     * @param result    结果
     */
    public void notify(Enum<LotteryEventType> eventType, LotteryResult result) {
        List<ILotteryEvenListener> users = listenersMap.get(eventType);
        for (ILotteryEvenListener listener : users) {
            listener.doEvent(result);
        }
    }
}