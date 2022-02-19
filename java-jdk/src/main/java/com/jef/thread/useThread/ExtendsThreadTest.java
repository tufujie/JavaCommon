package com.jef.thread.useThread;

import com.jef.business.BusinessDemo;
import com.jef.util.TimeUtil;

import java.util.Date;

/**
 * 多线程实现方式1，继承Thread类创建线程
 * Thread类本质上是实现了Runnable接口的一个实例，代表一个线程的实例。启动线程的唯一方法就是通过Thread类的start()实例方法。start()方法是一个native方法，它将启动一个新线程，并执行run()方法。这种方式实现多线程很简单，通过自己的类直接extend Thread，并复写run()方法，就可以启动新线程并执行自己定义的run()方法。例如：
 *
 * @author Jef
 * @date 20181228
 */
public class ExtendsThreadTest extends Thread {

    private Integer taskNum;

    public ExtendsThreadTest(Integer taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        BusinessDemo.taskHasReturn(taskNum, "Extend Thread");
    }

    public static void main(String[] args) {
        Date dateStart = new Date();
        int taskNum = 5;
        for (int i = 1; i <= taskNum; i++) {
            Thread thread = new ExtendsThreadTest(i);
            thread.setName("Thread.setName() num" + i);
            thread.start();
        }
        Date dateEnd = new Date();
        TimeUtil.printAllUseTime(dateStart, dateEnd);
    }

}