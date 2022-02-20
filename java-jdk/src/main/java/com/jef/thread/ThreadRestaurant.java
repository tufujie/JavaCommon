package com.jef.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.jef.util.PrintUtil.print;
import static com.jef.util.PrintUtil.printnb;

/**
 * 膳食
 */
class Meal {
    private final int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "膳食 " + orderNum;
    }
}

/**
 * 服务员
 */
class WaitPerson implements Runnable {
    private ThreadRestaurant restaurant;

    public WaitPerson(ThreadRestaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        // ... 让厨师制作膳食
                        wait();
                    }
                }
                print("服务员拿到 " + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    // 准备做另一道膳食
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("服务员中断");
        }
    }
}

/**
 * 厨师
 */
class Chef implements Runnable {
    private ThreadRestaurant restaurant;
    private int count = 0;

    public Chef(ThreadRestaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        // ... 等待服务员来拿膳食
                        wait();
                    }
                }
                // 假设一天只能做10个菜
                if (++count > 10) {
                    print("超出能做菜的盘数，关闭中");
                    restaurant.exec.shutdownNow();
                }
                printnb("膳食做好了! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("厨师中断");
        }
    }
}

/**
 * 任务合作的生产者-消费者方法
 *
 * @author Jef
 * @date 2022/2/20
 */
public class ThreadRestaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);

    public ThreadRestaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new ThreadRestaurant();
    }
}