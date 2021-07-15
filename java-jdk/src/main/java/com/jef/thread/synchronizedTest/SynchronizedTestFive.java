package com.jef.thread.synchronizedTest;
/**
 * 类锁：synchronized(*.class)代码块
 * @author Jef
 * @date 2020/7/23
 */
public class SynchronizedTestFive implements Runnable {
    static SynchronizedTestFive st1 = new SynchronizedTestFive();
    static SynchronizedTestFive st2 = new SynchronizedTestFive();

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

    public void method() {
        synchronized(SynchronizedTestFive.class) {
            System.out.println("开始执行:" + Thread.currentThread().getName());
            try {
                // 模拟执行内容
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("执行结束:" + Thread.currentThread().getName());
        }
    }
}