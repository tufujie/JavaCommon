package com.jef.thread.waxomatic2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.jef.util.PrintUtil.print;
import static com.jef.util.PrintUtil.printnb;

class Car {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    /**
     * 进行涂蜡
     */
    public void waxed() {
        lock.lock();
        try {
            // 准备抛光
            waxOn = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 进行抛光
     */
    public void buffed() {
        lock.lock();
        try {
            // 准备好再涂一层蜡
            waxOn = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 准备涂蜡
     */
    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * 准备抛光
     */
    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            while (waxOn) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("开始涂蜡! ");
                // 模拟需要涂蜡的时间
                TimeUnit.MILLISECONDS.sleep(200);

                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("通过中断退出涂蜡");
        }
        print("完成涂蜡任务");
    }
}

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();

                printnb("开始抛光! ");
                // 模拟需要抛光的时间
                TimeUnit.MILLISECONDS.sleep(200);

                car.buffed();
            }
        } catch (InterruptedException e) {
            print("通过中断退出抛光");
        }
        print("完成抛光任务");
    }
}

/**
 * 使用锁和条件对象完成任务合作
 *
 * @author Jef
 * @date 2022/2/20
 */
public class WaxOMatic2 {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(2);
        exec.shutdownNow();
    }
}