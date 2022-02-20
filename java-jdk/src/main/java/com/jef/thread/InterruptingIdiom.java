package com.jef.thread;

import com.jef.util.ExceptionUtil;

import java.util.concurrent.TimeUnit;

import static com.jef.util.PrintUtil.print;

class NeedsCleanup {
    private final int id;

    public NeedsCleanup(int ident) {
        id = ident;
        print("需要清理 " + id);
    }

    public void cleanup() {
        print("开始清理 " + id);
    }
}

class Blocked3 implements Runnable {
    private volatile double d = 0.0;

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // 第一个点
                NeedsCleanup n1 = new NeedsCleanup(1);
                // 在定义n1后立即开始“最后一次尝试”，以确保正确清理n1：
                try {
                    print("睡眠中。。。");
                    TimeUnit.SECONDS.sleep(1);
                    // 第二个点
                    NeedsCleanup n2 = new NeedsCleanup(2);
                    // 确保正确清理n2:
                    try {
                        print("计算中。。。");
                        // 耗时、无阻塞的操作：
                        for (int i = 1; i < 2500000; i++) {
                            d = d + (Math.PI + Math.E) / d;
                        }
                        print("完成了耗时的操作");
                    } finally {
                        n2.cleanup();
                    }
                } finally {
                    n1.cleanup();
                }
            }
            print("通过while()测试退出");
        } catch (InterruptedException e) {
            print("通过中断异常退出");
            ExceptionUtil.getExceptionStackTraceMessage(e);
        }
    }
}

/**
 * 打断任务的典型方式
 *
 * @author Jef
 * @date 2022/2/20
 */
public class InterruptingIdiom {
    public static void main(String[] args) throws Exception {
        /*if (args.length != 1) {
            print("用法：Java InterruptingIdiom毫秒延迟");
            System.exit(1);
        }*/
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(new Integer(1100));
        t.interrupt();
    }
}