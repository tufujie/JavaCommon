package com.jef.thread;

import com.jef.business.BusinessDemo;
import com.jef.util.TimeUtil;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Jef
 * @date 2021/1/2
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟三秒");
            }
        }, 3, TimeUnit.SECONDS);
        final Date[] now = {new Date()};
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟 1 秒后每三秒执行一次");
                TimeUtil.printAllUseTime(now[0]);
                now[0] = new Date();
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

}