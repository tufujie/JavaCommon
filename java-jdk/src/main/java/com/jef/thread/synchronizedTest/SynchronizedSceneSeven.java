package com.jef.thread.synchronizedTest;

/**
 * 方法抛出异常后，会释放锁吗
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedSceneSeven implements Runnable {
    static SynchronizedSceneSeven ss1 = new SynchronizedSceneSeven();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(ss1);
        Thread t2 = new Thread(ss1);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("运行完成");
    }

    @Override
    public void run() {
        method1();
    }

    public synchronized void method1() {
        System.out.println("method1开始执行:" + Thread.currentThread().getName());
        try {
            // 模拟执行内容
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 模拟异常
        throw new RuntimeException();
        //System.out.println("method1执行结束:" + Thread.currentThread().getName());
    }
}