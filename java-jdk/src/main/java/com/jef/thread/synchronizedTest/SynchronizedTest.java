package com.jef.thread.synchronizedTest;
/**
 * 不使用synchronized,两个线程同时a++
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedTest implements Runnable {
    static SynchronizedTest st = new SynchronizedTest();

    static int a = 0;

    /**
     * 不使用synchronized,两个线程同时a++
     */
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(a);
    }

    @Override
    public void run() {
        for (int i=0; i < 10000; i++) {
            a++;
        }
    }
}