package com.jef.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable {
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator g, int ident) {
        generator = g;
        id = ident;
    }

    /**
     * 把非偶数（奇数）输出来
     */
    @Override
    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if (val % 2 != 0) {
                System.out.println("产生了奇数=" + val);
                generator.cancel();
            } else {
                System.out.println(val + " 是偶数");
            }
        }
    }

    /**
     * 传入Int产生对象和一个整数
     * @param generator
     * @param count
     */
    public static void test(IntGenerator generator, int count) {
        System.out.println("Press Control-C to exit");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(generator, count));
        }
        exec.shutdown();
    }

    /**
     * 重载上述方法
     * @param generator
     */
    public static void test(IntGenerator generator) {
        test(generator, 10);
    }
}
