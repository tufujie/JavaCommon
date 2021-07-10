package com.jef.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSleepTest implements Runnable {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            exec.execute(new ThreadSleepTest());
        }
        exec.shutdown();
    }

    protected int countDown = 5;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public ThreadSleepTest() {

    }

    public ThreadSleepTest(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id +  "(" +(countDown > 0 ?  countDown + ")，" : "End!)\n") ;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            try {
                // 使任务终止执行给定的时间
                // 每个任务都将要睡眠（即阻塞），这使得线程调度器可以切换到另一个线程，进而驱动另一个任务
                Thread.sleep(100);
                // 指定sleep()延迟的时间单元
                // TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
