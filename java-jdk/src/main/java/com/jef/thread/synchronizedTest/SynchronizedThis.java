package com.jef.thread.synchronizedTest;
/**
 * 对象锁实例: 代码块形式
 *
 * @author Jef
 */
public class SynchronizedThis implements Runnable {
    static SynchronizedThis st = new SynchronizedThis();

    private String name;

    public static void main(String[] args) {
        Thread t1 = new Thread(st);
        Thread t2 = new Thread(st);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()) {

        }
        System.out.println("run over");

    }

    @Override
    public void run() {
        this.name = "test";
        synchronized (this) {
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
}