package com.jef.thread.juc;

import java.util.concurrent.Semaphore;

/**
 * @author Jef
 * @date 2023/5/14
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        int n = 4;
        Semaphore semaphore = new Semaphore(n);
        for (int i = 0; i < n; i++) {
            System.out.println(i + " do something");
            semaphore.acquire();
            System.out.println(i + " finish something");
            semaphore.release();
        }
        System.out.println("end");
    }

}