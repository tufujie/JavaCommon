package com.jef.thread.threadLocal;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Accessor implements Runnable {
    private final int id;

    public Accessor(int idn) {
        this.id = idn;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ":" + ThreadLocalVariableHolder.get();
    }

}

public class ThreadLocalVariableHolder {
    // 初始化一个一万以内的随机整数
    private static ThreadLocal<Integer> integerThreadLocal = new ThreadLocal<Integer>() {
        private Random random = new Random(47);

        protected synchronized Integer initialValue() {
            return random.nextInt(10000);
        }
    };

    public static void increment() {
        integerThreadLocal.set(integerThreadLocal.get() + 1);
    }

    public static int get() {
        return integerThreadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Accessor(i));
        }
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }
}



