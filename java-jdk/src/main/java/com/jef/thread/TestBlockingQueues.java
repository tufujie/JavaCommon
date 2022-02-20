package com.jef.thread;

import com.jef.thread.useThread.ImplRunnableTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static com.jef.util.PrintUtil.print;

class ImplRunnableTestRunner implements Runnable {
    private BlockingQueue<ImplRunnableTest> blockingQueue;

    public ImplRunnableTestRunner(BlockingQueue<ImplRunnableTest> queue) {
        blockingQueue = queue;
    }

    /**
     * 添加进队列中
     *
     * @param ir Runable对象
     */
    public void add(ImplRunnableTest ir) {
        try {
            blockingQueue.put(ir);
        } catch (InterruptedException e) {
            print("在put()过程中发生了中断");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                ImplRunnableTest rocket = blockingQueue.take();
                // 使用这个线程
                rocket.run();
            }
        } catch (InterruptedException e) {
            print("从take()方法唤醒");
        }
        print("退出ImplRunnableTestRunner");
    }
}

/**
 * @author Jef
 * @date 2022/2/20
 */
public class TestBlockingQueues {
    static void getkey() {
        try {
            // 弥补Windows/Linux在以下方面的差异：
            // 回车键生成的结果的长度：
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (java.io.IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void getkey(String message) {
        print(message);
        getkey();
    }

    static void test(String msg, BlockingQueue<ImplRunnableTest> queue) {
        print("消息=" + msg);
        ImplRunnableTestRunner runner = new ImplRunnableTestRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        for (int i = 0; i < 5; i++) {
            runner.add(new ImplRunnableTest(i));
        }
        getkey("点击 '回车' (" + msg + ")");
        t.interrupt();
        print("完成 " + msg + " 测试");
    }

    public static void main(String[] args) {
        // 未限制大小
        test("LinkedBlockingQueue", new LinkedBlockingQueue<ImplRunnableTest>());
        // 固定大小
        test("ArrayBlockingQueue", new ArrayBlockingQueue<ImplRunnableTest>(3));
        // 大小为1
        test("SynchronousQueue", new SynchronousQueue<ImplRunnableTest>());
    }
}