package com.jef.thread.useThread;

import com.jef.thread.ThreadYieldTest;

import java.util.concurrent.*;

public class CachedThreadPoolTest {
    public static void main(String[] args) {
        // CachedThreadPool为每个任务都创建一个线程
        // 在程序执行过程中通常会创建与所需数量相同的线程，然后在它回收旧线程时停止创建新线程，因此它是合理的Executor首选
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            executor.execute(new ThreadYieldTest());
        }
        executor.shutdown();
    }
}
