package com.jef.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步处理
 *
 * @author Jef
 * @create 2018/6/5 8:46
 */
public class AsyncHandler {
    private ExecutorService executor = Executors.newFixedThreadPool(1);

    /**
     * 简单求和，异步
     * @throws InterruptedException
     */
    public void asyncTask(int num) {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i = 0; i < num; i++) {
                    sum += i;
                }
                System.out.println("asyncTask end, sum=" + sum);
            }
        });
    }

    /**
     * 简单求和，同步
     * @throws InterruptedException
     */
    public void syncTask(int num) {
        int sum = 0;
        for(int i = 0; i < num; i++) {
            sum += i;
        }
        System.out.println("syncTask end, sum=" + sum);
    }

    /**
     * 异步，实现Runable的方式
     * @param num
     */
    public void asyncTaskTwo(int num) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int sum = 0;
                for(int i = 0; i < num; i++) {
                    sum += i;
                }
                System.out.println("asyncTaskTwo end, sum=" + sum);
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        AsyncHandler executor = new AsyncHandler();
        int num = 1000;
        // 代码上异步放在同步上面，用以查看结果，可以发现，主线程不会在异步任务阻塞，不需要等待异步任务完成，可以往下执行同步任务
//        executor.asyncTask(num);
        // 也可以使用asyncTaskTwo，这种比较精简
        executor.asyncTaskTwo(num);
        // 也可以使用这种方式
/*        RunableImpl runableImpl = new RunableImpl(num);
        new Thread(runableImpl).start();*/
        executor.syncTask(num);
    }

}
