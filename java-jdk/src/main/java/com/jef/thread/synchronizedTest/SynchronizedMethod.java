package com.jef.thread.synchronizedTest;

import com.jef.business.BusinessDemo;

/**
 * 两个线程同时访问一个对象的相同的synchronized方法
 * 所有的synchronized方法共享一把锁
 *
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedMethod implements Runnable {
    static SynchronizedMethod synchronizedMethod = new SynchronizedMethod();

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        BusinessDemo.taskHasReturn("SynchronizedMethod");
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(synchronizedMethod);
        Thread t2 = new Thread(synchronizedMethod);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("运行完成");
    }
}