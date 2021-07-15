package com.jef.thread.useThread;

import com.jef.thread.ThreadYieldTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolTest {
    public static void main(String[] args) {
        // FixedThreadPool使用了有限的线程集来执行所提交的任务
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new ThreadYieldTest());
        }
        executor.shutdown();
    }
}
