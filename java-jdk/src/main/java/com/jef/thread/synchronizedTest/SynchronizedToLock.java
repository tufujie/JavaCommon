package com.jef.thread.synchronizedTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * method1 等价于 method2
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedToLock {
    Lock lock = new ReentrantLock();

    public synchronized void method1() {
        System.out.println("执行method1");
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println("执行method2");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SynchronizedToLock sl = new SynchronizedToLock();
        // method1 等价于 method2
        sl.method1();
        sl.method2();
    }
}