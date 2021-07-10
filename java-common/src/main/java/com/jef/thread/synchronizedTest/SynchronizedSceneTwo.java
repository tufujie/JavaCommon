package com.jef.thread.synchronizedTest;

/**
 * 两个线程同时访问两个对象的相同的synchronized方法
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedSceneTwo implements Runnable {
    static SynchronizedSceneTwo ss1 = new SynchronizedSceneTwo();
    static SynchronizedSceneTwo ss2 = new SynchronizedSceneTwo();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(ss1);
        Thread t2 = new Thread(ss2);
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