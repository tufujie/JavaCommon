package com.jef.thread;

import com.jef.business.BusinessDemo;

import java.util.concurrent.Callable;

/**
 * 业务调用，一般放在实际业务中的类中
 * @author Jef
 * @date 2021/1/2
 */
public class MyBusinessCallable implements Callable<Object> {
    /**
     * 当前任务号
     */
    private final Integer taskNum;

    public MyBusinessCallable(Integer taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * 调用实际的业务
     */
    @Override
    public Object call() throws Exception {
        return BusinessDemo.taskHasReturn(taskNum, "Callable");
    }
}