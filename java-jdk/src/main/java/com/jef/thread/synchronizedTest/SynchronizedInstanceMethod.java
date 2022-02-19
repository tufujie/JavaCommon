package com.jef.thread.synchronizedTest;

import com.jef.business.BusinessDemo;

/**
 * 对象锁实例：synchronized方法
 *
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedInstanceMethod implements Runnable {
    static SynchronizedInstanceMethod st = new SynchronizedInstanceMethod();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("运行完成");
    }

    @Override
    public void run(){
        method();
    }

    public synchronized void method() {
        BusinessDemo.taskHasReturn("SynchronizedInstanceMethod");
    }
}