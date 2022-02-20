package com.jef.thread.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.jef.util.PrintUtil.print;
import static com.jef.util.PrintUtil.printnb;

class Car {
    private boolean waxOn = false;

    /**
     * 进行涂蜡
     */
    public synchronized void waxed() {
        // 准备抛光
        waxOn = true;
        notifyAll();
    }

    /**
     * 进行抛光
     */
    public synchronized void buffed() {
        // 准备好再涂一层蜡
        waxOn = false;
        notifyAll();
    }

    /**
     * 准备涂蜡
     */
    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    /**
     * 准备抛光
     */
    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car c) {
        car = c;
    }

    @Override
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

    @Override
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
 * 基本任务合作
 *
 * @author Jef
 * @date 2022/2/20
 */
public class WaxOMatic {
    public static void main(String[] args) throws Exception {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        // while循环遍历
        TimeUnit.SECONDS.sleep(2);
        // 中断所有任务
        exec.shutdownNow();
    }
}