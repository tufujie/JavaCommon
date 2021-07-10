package com.jef.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Jef
 * @date 2019/1/31
 */
public class LockDemo {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    // 注意这个地方
    private Lock lock = new ReentrantLock();
    private static Integer NUM = 10;
    public static void main(String[] args)  {
        final LockDemo test = new LockDemo();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                test.insertTryLock(Thread.currentThread());
            }
        }.start();
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName() + "得到了锁");
            for(int i=0; i < NUM; i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println(thread.getName() + "释放了锁");
            lock.unlock();
        }
    }

    public void insertTryLock(Thread thread) {
        if (lock.tryLock()) {
            try {
                System.out.println("tryLock，" + thread.getName() + "得到了锁");
                for(int i=0; i < NUM; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                System.out.println("tryLock，" + thread.getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println("tryLock，" + thread.getName() + "获取锁失败");
        }
    }
}