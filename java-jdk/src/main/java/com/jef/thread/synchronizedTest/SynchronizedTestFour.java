package com.jef.thread.synchronizedTest;
/**
 * 类锁：synchronized加载static方法上
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedTestFour implements Runnable {
    static SynchronizedTestFour st1 = new SynchronizedTestFour();
    static SynchronizedTestFour st2 = new SynchronizedTestFour();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(st1);
        Thread t2 = new Thread(st2);
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

    public static synchronized void method() {
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