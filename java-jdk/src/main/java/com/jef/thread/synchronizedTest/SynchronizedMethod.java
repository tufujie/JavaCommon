package com.jef.thread.synchronizedTest;

/**
 * 两个线程同时访问一个对象的相同的synchronized方法
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedMethod implements Runnable {
    static SynchronizedMethod ss = new SynchronizedMethod();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(ss);
        Thread t2 = new Thread(ss);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("run over");
    }

    @Override
    public void run() {
        method();
    }

    public synchronized void method() {
        System.out.println("开始执行:" + Thread.currentThread().getName());
        try {
            // 模拟执行内容
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("执行结束:" + Thread.currentThread().getName());
    }
}