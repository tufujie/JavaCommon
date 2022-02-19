package com.jef.thread.useThread;

import com.jef.business.BusinessDemo;
import com.jef.util.TimeUtil;

import java.util.Date;

/**
 * 多线程实现方式2，实现Runnable接口创建线程
 * 如果自己的类已经extends另一个类，就无法直接extends Thread，此时，可以实现一个Runnable接口，如下
 * 为了启动ImplRunnableTest，需要首先实例化一个Thread，并传入自己的ImplRunnableTest实例
 *
 * @author Jef
 * @date 20181228
 */
public class ImplRunnableTest implements Runnable {

    private Integer taskNum;

    public ImplRunnableTest(Integer taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        BusinessDemo.taskHasReturn(taskNum, "Impl Runable");
        Thread.yield();
    }

    public static void main(String[] args) {
        Date dateStart = new Date();
        int taskNum = 5;
        for (int i = 1; i <= taskNum; i++) {
            Thread thread = new Thread(new ImplRunnableTest(i));
            thread.start();
        }
        Date dateEnd = new Date();
        TimeUtil.printAllUseTime(dateStart, dateEnd);
    }
}