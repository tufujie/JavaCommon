package com.jef.thread.useThread.threadPool;

import com.jef.thread.useThread.ImplRunnableTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool使用了有限的线程集来执行所提交的任务
 *
 * @author Jef
 * @date 2022/2/19
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executor.execute(new ImplRunnableTest(i));
        }
        executor.shutdown();
    }
}
