package com.jef.thread.useThread;

import com.jef.business.BusinessDemo;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTest {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<Integer>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<Integer> future = exec.submit(new TaskWithResult(i));
            futureList.add(future);
        }
        for (Future<Integer> future : futureList) {
            try {
                // 直接调用get(，在这种情况下，get()将阻塞，直至结果准备就绪
                System.out.println("Future.get()=" + future.get());
                // 用isDone0方法来查询Future是否已经完成
                System.out.println("Future.isDone()" + future.isDone());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
            }
        }
    }
}

/**
 * 多线程实现方式3，实现Callable接口
 *
 * @author Jef
 * @date 2022/2/19
 */
class TaskWithResult implements Callable<Integer> {
    private int num;

    public TaskWithResult(int num) {
        this.num = num;
    }

    @Override
    public Integer call() throws Exception {
        return BusinessDemo.taskHasReturn(num, "Callable Future");
    }
}