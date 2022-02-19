package com.jef.thread.deamon;

import com.jef.business.BusinessDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeamonFromFactory implements Runnable {
    private int num;

    public DeamonFromFactory(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BusinessDemo.taskHasReturn(num, "DeamonThreadFactory");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool(new DeamonThreadFactory());
        for (int i = 0; i < 10; i++) {
            executor.execute(new DeamonFromFactory(i));
        }
        System.out.println("所有的后台进程启动");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
