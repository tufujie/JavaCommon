package com.jef.thread.synchronizedTest;

import static com.jef.util.PrintUtil.print;

/**
 * 同一个互斥可以如何能被同一个任务多次获得
 *
 * @author Jef
 * @date 2022/2/20
 */


public class SynchronizedMultiLock {

    public synchronized void method1(int count) {
        if (count-- > 0) {
            print("方法1调用方法2,count=" + count);
            method2(count);
        }
    }

    public synchronized void method2(int count) {
        if (count-- > 0) {
            print("方法2调用方法1,count=" + count);
            method1(count);
        }
    }

    public static void main(String[] args) throws Exception {
        final SynchronizedMultiLock multiLock = new SynchronizedMultiLock();
        new Thread() {
            public void run() {
                multiLock.method1(10);
            }
        }.start();
    }
}