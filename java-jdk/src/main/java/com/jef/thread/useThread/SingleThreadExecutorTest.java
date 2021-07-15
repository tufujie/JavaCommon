package com.jef.thread.useThread;

import com.jef.thread.ThreadYieldTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorTest {
    public static void main(String[] args) {
        // 就像是线程数量为1的FixedThreadPool
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++) {
            executor.execute(new ThreadYieldTest());
        }
        executor.shutdown();
    }
}
