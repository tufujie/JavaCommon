package com.jef.thread;

/**
 * ThreadLocal的demo
 * 1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
 * 2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像代码中的longLocal和stringLocal；
 * 3）在进行get之前，必须先set，否则会报空指针异常；
 * @author Jef
 * @date 20190131
 */
public class ThreadLocalDemo {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();


        threadLocalDemo.set();
        printThreadLocal(threadLocalDemo);

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                threadLocalDemo.set();
                printThreadLocal(threadLocalDemo);
            }
        };
        thread1.start();
        thread1.join();
        printThreadLocal(threadLocalDemo);
    }

    /**
     * 打印出threadLocal信息
     * @author Jef
     * @date 2021/3/31
     * @param threadLocalDemo
     * @return void
     */
    public static void printThreadLocal(ThreadLocalDemo threadLocalDemo) {
        System.out.printf("线程ID=%s,线程名称=%s\n", threadLocalDemo.getLong(), threadLocalDemo.getString());
    }
}