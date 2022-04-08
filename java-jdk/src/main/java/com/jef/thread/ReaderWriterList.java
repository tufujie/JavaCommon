package com.jef.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.jef.util.PrintUtil.print;

public class ReaderWriterList<T> {
    private ArrayList<T> lockedList;
    // 公平订购
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public ReaderWriterList(int size, T initialValue) {
        lockedList = new ArrayList<T>(Collections.nCopies(size, initialValue));
    }

    public T set(int index, T element) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return lockedList.set(index, element);
        } finally {
            wlock.unlock();
        }
    }

    public T get(int index) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            // 显示多个读者，可能会获取读锁：
            if (lock.getReadLockCount() > 1) {
                print("lock.getReadLockCount()=" + lock.getReadLockCount());
            }
            return lockedList.get(index);
        } finally {
            rlock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        new ReaderWriterListTest(30, 1);
    }
}

class ReaderWriterListTest {
    ExecutorService exec = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private static Random rand = new Random(47);
    private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE, 0);

    private class Writer implements Runnable {

        @Override
        public void run() {
            try {
                // 2秒测试
                for (int i = 0; i < 20; i++) {
                    list.set(i, rand.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                // 可以接受的退出方式
            }
            print("写入结束， 关闭");
            exec.shutdownNow();
        }
    }

    private class Reader implements Runnable {

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    for (int i = 0; i < SIZE; i++) {
                        list.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                }
            } catch (InterruptedException e) {
                // 可以接受的退出方式
            }
        }
    }

    public ReaderWriterListTest(int readers, int writers) {
        for (int i = 0; i < readers; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < writers; i++) {
            exec.execute(new Writer());
        }
    }
}