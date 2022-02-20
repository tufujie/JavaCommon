package com.jef.thread.synchronizedTest;

import com.jef.business.BusinessDemo;

/**
 * 两个线程同时访问两个对象的相同的synchronized方法
 *
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedMethodTwo implements Runnable {
    static SynchronizedMethodTwo ss1 = new SynchronizedMethodTwo();
    static SynchronizedMethodTwo ss2 = new SynchronizedMethodTwo();

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        BusinessDemo.taskHasReturn("SynchronizedMethod");
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(ss1);
        Thread t2 = new Thread(ss2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}