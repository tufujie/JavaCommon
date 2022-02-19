package com.jef.thread.synchronizedTest;

import com.jef.business.BusinessDemo;

/**
 * 类锁：synchronized(*.class)代码块
 *
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedClass implements Runnable {
    static SynchronizedClass st1 = new SynchronizedClass();
    static SynchronizedClass st2 = new SynchronizedClass();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(st1);
        Thread t2 = new Thread(st2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("运行完成");
    }

    @Override
    public void run() {
        method();
    }

    public void method() {
        synchronized (SynchronizedClass.class) {
            BusinessDemo.taskHasReturn("SynchronizedClass");
        }
    }
}