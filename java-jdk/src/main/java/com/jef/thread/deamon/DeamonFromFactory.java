package com.jef.thread.deamon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeamonFromFactory implements Runnable {
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + ":" + this);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool(new DeamonThreadFactory());
        for(int i = 0; i < 10; i++)
            exec.execute(new DeamonFromFactory());
        System.out.println("All daemon start");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
