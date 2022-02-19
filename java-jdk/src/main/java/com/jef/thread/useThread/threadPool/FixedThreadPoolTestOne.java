package com.jef.thread.useThread.threadPool;

import com.jef.thread.MyBusinessCallable;
import com.jef.util.TimeUtil;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用ExecutorService、Callable、Future实现有返回结果的线程
 * xecutorService、Callable、Future三个接口实际上都是属于Executor框架。返回结果的线程是在JDK1.5中引入的新特征，有了这种特征就不需要再为了得到返回值而大费周折了。而且自己实现了也可能漏洞百出。
 * 可返回值的任务必须实现Callable接口。类似的，无返回值的任务必须实现Runnable接口。
 * 执行Callable任务后，可以获取一个Future的对象，在该对象上调用get就可以获取到Callable任务返回的Object了。
 * 注意：get方法是阻塞的，即：线程无返回结果，get方法会一直等待。
 * 再结合线程池接口ExecutorService就可以实现传说中有返回结果的多线程了。
 * 下面提供了一个完整的有返回结果的多线程测试例子，在JDK1.5下验证过没问题可以直接使用。代码如下：
 * @author Jef
 * @date 20181228
 */
public class FixedThreadPoolTestOne {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Date dateStart = new Date();
        int taskSize = 3;
        // 创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        for (int i = 1; i <= taskSize; i++) {
            Callable callable = new MyBusinessCallable(i);
            // 执行任务并获取Future对象
            // 从Future对象上获取任务的返回值，并输出到控制台
            Future f = pool.submit(callable);
            System.out.println("Future.get()=" + f.get().toString());
        }
        // 关闭线程池
        pool.shutdown();
        TimeUtil.printAllUseTime(dateStart);
    }
}
