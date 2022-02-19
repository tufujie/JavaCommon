package com.jef.thread.useThread.threadPool;

import com.jef.business.BusinessDemo;
import com.jef.util.ThreadUtil;
import com.jef.util.TimeUtil;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 使用线程池
 * @author Jef
 * @date 2018/12/14 17:23
 */
public class UseAliThreadPoolTest {
    public static void main(String[] args) {
        new UseAliThreadPoolTest().testTwo();
    }

    /**
     * 使用工具的线程池
     */
    public void test() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Date dateStart = new Date();
        int taskNum = 10;
        for (int i = 1; i <= taskNum; i++) {
            try {
                int finalI = i;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        BusinessDemo.taskNoReturn(finalI);
                    }
                };
                ThreadUtil.executeThread(i + "", runnable);
            } catch (Exception e) {
                System.out.println("异常信息=" + e.getMessage());
            } finally {
                countDownLatch.countDown();
            }
        }
        Date dateEnd = new Date();
        TimeUtil.printAllUseTime(dateStart, dateEnd);
    }

    /**
     * 使用工具的线程池
     */
    public void testTwo() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Date dateStart = new Date();
        int taskNum = 10;
        for (int i = 1; i <= taskNum; i++) {
            try {
                int finalI = i;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        BusinessDemo.taskNoReturn(finalI);
                    }
                };
                ThreadUtil.executeThreadTwo(i + "", runnable);
            } catch (Exception e) {
                System.out.println("异常信息=" + e.getMessage());
            } finally {
                countDownLatch.countDown();
            }
        }
        Date dateEnd = new Date();
        TimeUtil.printAllUseTime(dateStart, dateEnd);
    }
}
