package com.jef.thread.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int getValue() {
        return atomicInteger.get();
    }

    /**
     * 增加偶数2
     */
    private void evenIncrement() {
        atomicInteger.addAndGet(2);
    }

    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.println("终止");
                System.exit(0);
            }
        }, 5000);
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest ait = new AtomicIntegerTest();
        exec.execute(ait);
        while (true) {
            int val = ait.getValue();
            if (val % 2 != 0) {
                System.out.println("产生了奇数=" + val);
                System.exit(0);
            } else {
                System.out.println(val + " 是偶数");
            }
        }
    }
}
