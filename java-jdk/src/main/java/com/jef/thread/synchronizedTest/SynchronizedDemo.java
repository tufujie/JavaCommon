package com.jef.thread.synchronizedTest;

import java.util.ArrayList;

/**
 * 插入测试
 *
 * @author Jef
 * @date 2019/1/31
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        final SynchronizedInsertData insertData = new SynchronizedInsertData();

        new Thread() {
            @Override
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----------------");
        new Thread() {
            @Override
            public void run() {
                insertData.insertSynchronized(Thread.currentThread());
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                insertData.insertSynchronized(Thread.currentThread());
            }
        }.start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("----------------");
        new Thread() {
            @Override
            public void run() {
                insertData.insertSynchronizedObject(Thread.currentThread());
            }
        }.start();


        new Thread() {
            @Override
            public void run() {
                insertData.insertSynchronizedThis(Thread.currentThread());
            }
        }.start();
    }
}

class SynchronizedInsertData {
    private static Integer insertNum = 20;
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private static Object object = new Object();

    public void insert(Thread thread) {
        for (int i = 0; i < insertNum; i++) {
            System.out.println(thread.getName() + "在插入数据" + i);
            arrayList.add(i);
        }
    }

    public synchronized void insertSynchronized(Thread thread) {
        for (int i = 0; i < insertNum; i++) {
            System.out.println("使用syncthronized，" + thread.getName() + "在插入数据" + i);
            arrayList.add(i);
        }
        insertSynchronizedObject(thread);
    }

    public void insertSynchronizedObject(Thread thread) {
        synchronized (object) {
            for (int i = 0; i < insertNum; i++) {
                System.out.println("使用syncthronized object，" + thread.getName() + "在插入数据" + i);
                arrayList.add(i);
            }
        }
    }

    public void insertSynchronizedThis(Thread thread) {
        synchronized (this) {
            for (int i = 0; i < insertNum; i++) {
                System.out.println("使用syncthronized this，" + thread.getName() + "在插入数据" + i);
                arrayList.add(i);
            }
        }
    }
}