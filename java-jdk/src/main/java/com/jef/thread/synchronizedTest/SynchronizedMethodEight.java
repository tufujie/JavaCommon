package com.jef.thread.synchronizedTest;

/**
 * 进入到被synchronized修饰的方法，这个方法里边调用了非synchronized方法，是线程安全的吗？
 *
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedMethodEight {

    public static synchronized void method1() {
        method2();
    }

    private static void method2() {
        System.out.println(Thread.currentThread().getName() + "进入非Synchronized方法");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束非Synchronized方法");
    }

    public static void main(String[] args) {
        new Thread(() -> {
            method1();
        }).start();

        new Thread(() -> {
            method1();
        }).start();
    }
}