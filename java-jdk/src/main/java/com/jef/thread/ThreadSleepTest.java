package com.jef.thread;

import com.jef.business.BusinessDemo;
import com.jef.util.TimeUtil;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSleepTest implements Runnable {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new ThreadSleepTest(i));
        }
        exec.shutdown();
    }

    private int num;

    public ThreadSleepTest(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            BusinessDemo.taskHasReturn(num, "Thread.sleep()");
            Date dateStart = new Date();
            // 休眠。使任务终止执行给定的时间，每个任务都将要睡眠（即阻塞），这使得线程调度器可以切换到另一个线程，进而驱动另一个任务
            Thread.sleep(1000);
            Date dateEnd = new Date();
            TimeUtil.printAllUseTime(dateStart, dateEnd);
            // 指定sleep()延迟的时间单元。imeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
