package com.jef.thread;

import com.jef.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleProperties implements Runnable {
    private int propertity;
    public SimpleProperties(int propertity) {
        this.propertity = propertity;
    }

    @Override
    public void run() {
        ThreadUtil.printCurrentThreadPriorty();
        Thread.currentThread().setPriority(propertity);
        ThreadUtil.printCurrentThreadPriorty();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimpleProperties(Thread.MAX_PRIORITY));
        }
        exec.shutdown();
    }
}
