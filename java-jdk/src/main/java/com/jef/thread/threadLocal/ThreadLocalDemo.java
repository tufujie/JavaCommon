package com.jef.thread.threadLocal;

import com.jef.constant.BasicConstant;

/**
 * ThreadLocal的demo
 * 1）实际的通过ThreadLocal创建的副本是存储在每个线程自己的threadLocals中的；
 * 2）为何threadLocals的类型ThreadLocalMap的键值为ThreadLocal对象，因为每个线程中可有多个threadLocal变量，就像代码中的longLocal和threadNameLocal；
 * 3）在进行get之前，必须先set，否则会报空指针异常；
 *
 * @author Jef
 * @date 20190131
 */
public class ThreadLocalDemo {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
    ThreadLocal<String> threadNameLocal = new ThreadLocal<String>();
    // 变量，这里是用户名
    ThreadLocal<String> userNameLocal = new ThreadLocal<String>();

    public void setThreadAndUserName(String userName) {
        longLocal.set(Thread.currentThread().getId());
        threadNameLocal.set(Thread.currentThread().getName());
        userNameLocal.set(userName);
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getThreadName() {
        return threadNameLocal.get();
    }


    public String getUserName() {
        return userNameLocal.get();
    }

    /**
     * 打印出threadLocal信息
     *
     * @param threadLocalDemo
     * @return void
     * @author Jef
     * @date 2021/3/31
     */
    public static void printThreadLocal(ThreadLocalDemo threadLocalDemo) {
        System.out.printf("线程ID=%s,线程名称=%s,用户名称=%s\n", threadLocalDemo.getLong(), threadLocalDemo.getThreadName(), threadLocalDemo.getUserName());
    }

    public static void main(String[] args) throws InterruptedException {
        final ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        threadLocalDemo.setThreadAndUserName(BasicConstant.USER_NAME);
        printThreadLocal(threadLocalDemo);

        for (int i = 1; i < 3; i++) {
            int finalI = i;
            Thread thread1 = new Thread(() -> {
                threadLocalDemo.setThreadAndUserName(finalI + BasicConstant.STR_ONE);
                printThreadLocal(threadLocalDemo);
            });
            thread1.start();
            thread1.join();
        }

        printThreadLocal(threadLocalDemo);
    }
}