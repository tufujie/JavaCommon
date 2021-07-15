package com.jef.thread;

import com.jef.business.BusinessDemo;

/**
 * 异步处理
 *
 * @author Jef
 * @create 2018/6/5 8:19
 */
public class RunableImpl implements Runnable {
    private int num;

    /**
     * @param num 业务所需参数
     */
    public RunableImpl(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BusinessDemo.taskOfDemo(num, "使用线程");
            }
        });
        thread.start();
    }
}
