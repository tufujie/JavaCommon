package com.jef.thread.useThread.threadPool;

import com.jef.thread.useThread.ImplRunnableTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 就像是线程数量为1的FixedThreadPool
 * 如果向SingleThreadExecutor提交了多个任务，那么这些任务将排队，每个任务都会在下一个任务开始之前运行结束，所有的任务将使用相同的线程。
 * 可以确保任意时刻在任何线程中都只有唯一的任务在运行
 *
 * @author Jef
 * @date 2022/2/19
 */
public class SingleThreadExecutorTest {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++) {
            executor.execute(new ImplRunnableTest(i));
        }
        executor.shutdown();
    }
}
