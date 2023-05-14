package com.jef.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Jef
 * @date 2023/5/14
 */
public class CyclicBarrierDemo extends Thread {

    public static void main(String[] args) {
        int n = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(n);
        for (int i = 0; i < n; i++) {
            new CyclicBarrierDemo(cyclicBarrier).start();
        }
    }

    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierDemo(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000); //以睡眠来模拟线程需要预定读入数据操作
            System.out.println("线程" + Thread.currentThread().getName() + "读入数据完毕，等待其他线程读入完毕");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("所有线程写入完毕，继续处理其他任务，比如数据合并等操作");
    }
}