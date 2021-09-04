package com.jef.thread.synchronizedTest;
/**
 * 对象锁实例：synchronized方法
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
        System.out.println("run over");
    }

    @Override
    public void run(){
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