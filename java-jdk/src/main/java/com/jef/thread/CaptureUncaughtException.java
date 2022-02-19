package com.jef.thread;

import com.jef.business.BusinessDemo;
import com.jef.util.PrintUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 异常线程
 *
 * @author Jef
 * @date 2022/2/19
 */
class ExceptionThread implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        BusinessDemo.taskHasReturn("Thread Exception");
        System.out.println("异常处理 = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

/**
 * 未捕获异常处理器
 */
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("未捕获异常处理 " + e);
    }
}

/**
 * 线程工厂
 */
class HandlerThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable runnable) {
        System.out.println(this + " 创建新线程");
        Thread t = new Thread(runnable);
        System.out.println("已创建 " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("异常处理 = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new HandlerThreadFactory());
        exec.execute(new ExceptionThread());

        PrintUtil.printSplitLine();
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
    }
}