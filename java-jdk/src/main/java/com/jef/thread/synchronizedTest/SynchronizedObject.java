package com.jef.thread.synchronizedTest;

import com.jef.business.BusinessDemo;

/**
 * 对象锁实例: 代码块形式
 *
 * @author Jef
 */
public class SynchronizedObject implements Runnable {
    static SynchronizedObject st = new SynchronizedObject();

    public static void main(String[] args) {
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {
            System.out.println("do something");
        }
        System.out.println("运行完成");

    }

    @Override
    public void run() {
        synchronized (new SynchronizedObject()) {
            BusinessDemo.taskHasReturn("SynchronizedObject");
        }
    }
}