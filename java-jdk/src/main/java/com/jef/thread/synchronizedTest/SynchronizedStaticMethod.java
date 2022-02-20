package com.jef.thread.synchronizedTest;

import com.jef.business.BusinessDemo;

/**
 * 类锁：synchronized加在static方法上
 *
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedStaticMethod implements Runnable {
    static SynchronizedStaticMethod st1 = new SynchronizedStaticMethod();
    static SynchronizedStaticMethod st2 = new SynchronizedStaticMethod();

    @Override
    public void run() {
        method();
    }

    public static synchronized void method() {
        BusinessDemo.taskHasReturn("SynchronizedMethod");
    }

    /**
     * 两个线程同时访问两个对象的相同的static的synchronized方法
     */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(st1);
        Thread t2 = new Thread(st2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}