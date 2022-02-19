package com.jef.thread.lock;

import com.jef.business.BusinessDemo;
import com.jef.util.ExceptionUtil;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的使用
 *
 * @author Jef
 * @date 2019/1/31
 */
public class LockDemo {
    // 注意这个地方
    private Lock lock = new ReentrantLock();

    public void insert(Thread thread) {
        lock.lock();
        try {
            BusinessDemo.taskHasReturn("Lock");
        } catch (Exception e) {
            ExceptionUtil.getExceptionStackTraceMessage("锁异常", e);
        } finally {
            System.out.println("lock，" + thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public void insertTryLock(Thread thread) {
        boolean locked = lock.tryLock();
        if (locked) {
            try {
                BusinessDemo.taskHasReturn("Lock");
            } catch (Exception e) {
                ExceptionUtil.getExceptionStackTraceMessage("锁异常", e);
            } finally {
                System.out.println("tryLock，" + thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println("tryLock，" + thread.getName() + "获取锁失败");
        }
    }

    public static void main(String[] args) {
        final LockDemo test = new LockDemo();

        new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                test.insertTryLock(Thread.currentThread());
            }
        }.start();
    }
}