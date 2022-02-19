package com.jef.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 尝试去获取琐，无设置时间
     */
    public void untimed() {
        boolean locked = lock.tryLock();
        try {
            System.out.println("tryLock(): " + locked);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }

    /**
     * 尝试去获取琐，设置时间
     */
    public void timed() {
        boolean locked = false;
        try {
            locked = lock.tryLock(2, TimeUnit.SECONDS);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("tryLock(2, TimeUnit.SECONDS): " + locked);
        } finally {
            if (locked) {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        final AttemptLocking al = new AttemptLocking();
        al.untimed(); // true
        al.timed();   // true
        // 创建一个分开的任务去获取lock
        new Thread() {
            {
                setDaemon(true);
            }

            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
        // true，示例代码中是说是false
        al.untimed();
        // true，示例代码中是说是false
        al.timed();
    }
}
