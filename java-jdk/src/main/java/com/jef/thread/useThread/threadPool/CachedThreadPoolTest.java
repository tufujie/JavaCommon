package com.jef.thread.useThread.threadPool;

import com.jef.thread.useThread.ImplRunnableTest;
import com.jef.util.ExceptionUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CachedThreadPool为每个任务都创建一个线程
 * 在程序执行过程中通常会创建与所需数量相同的线程，然后在它回收旧线程时停止创建新线程，因此它是合理的Executor首选
 *
 * @author Jef
 * @date 2022/2/19
 */
public class CachedThreadPoolTest {
    
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executor.execute(new ImplRunnableTest(i));
        }
        // 对shutdown()方法的调用可以防止新任务被提交给这个Executor
        executor.shutdown();
        try {
            executor.execute(new ImplRunnableTest(6));
        } catch (Exception e) {
            ExceptionUtil.getExceptionStackTraceMessage("newCachedThreadPool", e);
        }
    }
}
