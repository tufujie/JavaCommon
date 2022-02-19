package com.jef.thread;

import com.jef.business.BusinessDemo;

public class ThreadYieldTest implements Runnable {
    protected int num;

    public ThreadYieldTest(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        BusinessDemo.taskHasReturn(num, "Thread.yield()");
        /*
        Thread.yield()可以将CPU从一个线程转移给另一个线程
        在run()中对静态方法Thread.yieldO的调用是对线程调度器(Java线程机制的一 部分，可以将CPU从一一个线程转移给另一个线程) 的一种建议，
        它在声明:“我已经执行完生命周期中最重要的部分了，此刻正是切换给其他任务执行一段时间的大好时机。”
        不过这只是-一个暗示，没有任何机制保证它将会被采纳。
        这完全是选择性的，但是这里使用它是因为它会在这些示例中产生更加有趣的输出:你更有可能会看到任务换进换出的证据。
        */
        Thread.yield();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadYieldTest(i)).start();
        }
    }
}
