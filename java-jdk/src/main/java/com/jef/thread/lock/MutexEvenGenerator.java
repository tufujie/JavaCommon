package com.jef.thread.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的方法的正确使用
 * @author Jef
 * @date 20190131
 */
public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    // 第二种方式实现安全性增数
    private AtomicInteger currentEvenValueSecond = new AtomicInteger(0);
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        // lock放置在try外层
        lock.lock();
        try {
            // 内部业务处理
            // 现在的业务是每次currentEvenValue会加2，也就意味着currentEvenValue只可能是偶数
            ++currentEvenValue;
            Thread.yield();
            ++currentEvenValue;
            // return语句必须放在try子句中，如果放在最后，unlock已经发生了，会把数据暴露给第二个任务
            return currentEvenValue;
        } finally {
            // unlock必须放置在finally中
            lock.unlock();
        }
    }

    public int nextTwo() {
        return currentEvenValueSecond.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}
