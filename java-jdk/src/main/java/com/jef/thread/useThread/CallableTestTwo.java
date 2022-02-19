package com.jef.thread.useThread;

import com.jef.business.BusinessDemo;
import com.jef.util.TimeUtil;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 实现Callable接口通过FutureTask包装器来创建Thread线程
 * Callable接口（也只有一个方法）定义如下：
 *
 * @author Jef
 * @date 20181228
 */
public class CallableTestTwo<V> implements Callable {

    private Integer taskNum;

    public CallableTestTwo(Integer taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public Integer call() throws Exception {
        return BusinessDemo.taskHasReturn(taskNum, "Callable FutureTask");
    }

    public static void main(String[] args) {
        Date dateStart = new Date();
        int taskNum = 3;
        for (int i = 1; i <= taskNum; i++) {
            Callable<Integer> oneCallable = new CallableTestTwo<Integer>(i);
            // 由Callable<Integer>创建一个FutureTask<Integer>对象：
            FutureTask<Integer> oneTask = new FutureTask<Integer>(oneCallable);
            // 注释：FutureTask<Integer>是一个包装器，它通过接受Callable<Integer>来创建，它同时实现了Future和Runnable接口。
            // 由FutureTask<Integer>创建一个Thread对象：
            Thread oneThread = new Thread(oneTask);
            oneThread.start();
            // 至此，一个线程就创建完成了
        }
        Date dateEnd = new Date();
        TimeUtil.printAllUseTime(dateStart, dateEnd);
    }
}
