package com.jef.thread.deamon;

import com.jef.business.BusinessDemo;
import com.jef.util.ThreadUtil;

import java.util.concurrent.TimeUnit;

public class SimpleDaemons implements Runnable {
    private int num;

    public SimpleDaemons(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            // 如果main()函数还在执行，这里会一直执行
            while (true) {
                BusinessDemo.taskHasReturn(num, "Thread.setDaemon()");
                ThreadUtil.printCurrentThreadIsDaemon();
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Thread daemon = new Thread(new SimpleDaemons(i));
            // 必须在线程启动之前调用setDaemom()方法，才能把它设置为后台线程。
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("所有的后台进程启动");
        TimeUnit.MILLISECONDS.sleep(200);
        /*
        当最后-个非后台线程终止时，后台线程会“突然”终止。因此一旦main()退出，JVM就会立即关闭所有的后台进程，而不会有任何你希望出现的确认形式。
        因为你不能以优雅的方式来关闭后台进程，所以他们几乎不是一种好的思想
         */
    }
}
