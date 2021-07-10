package com.jef.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleProperties implements Runnable {
    private int countDown = 5;
    private volatile double d;
    private int propertity;
    public SimpleProperties(int propertity) {
        this.propertity = propertity;
    }

    public String toString() {
        return Thread.currentThread() + ":" + countDown;
    }

    public void run() {
        Thread.currentThread().setPriority(propertity);
        while (countDown-- > 0) {
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / i;
                if(i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new SimpleProperties(Thread.MIN_PRIORITY));
        exec.execute(new SimpleProperties(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}
