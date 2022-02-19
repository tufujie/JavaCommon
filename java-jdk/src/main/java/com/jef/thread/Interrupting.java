package com.jef.thread;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.jef.util.PrintUtil.print;

/**
 * 中断
 *
 * @author Jef
 * @date 2022/2/19
 */
class SleepBlocked implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            print("中断异常");
        }
        print("退出 SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream is) {
        in = is;
    }

    @Override
    public void run() {
        try {
            print("等待 read()");
            in.read();
        } catch (IOException e) {
            if (Thread.currentThread().isInterrupted()) {
                print("Interrupted from blocked I/O");
            } else {
                throw new RuntimeException(e);
            }
        }
        print("退出 IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {
        while (true) // Never releases lock
            Thread.yield();
    }

    public SynchronizedBlocked() {
        new Thread() {
            @Override
            public void run() {
                f();
            }
        }.start();
    }

    @Override
    public void run() {
        print("尝试调用f()方法");
        f();
        print("退出 SynchronizedBlocked.run()");
    }
}

public class Interrupting {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    static void test(Runnable runnable) throws InterruptedException {
        Future<?> future = executorService.submit(runnable);
        TimeUnit.MILLISECONDS.sleep(100);
        print("开始中断 " + runnable.getClass().getName());
        // 可以用来中断任务
        future.cancel(true);
        print("发送中断给 " + runnable.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        print("通过System.exit(0)中止");
        System.exit(0);
    }
}