package com.jef.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.jef.util.PrintUtil.print;
import static com.jef.util.PrintUtil.printnb;

class Letter {
    // 1,2代表输出A,B
    private int num = 0;

    /**
     * 输出A
     */
    public synchronized void printA() {
        // 准备输出B
        num = 1;
        notifyAll();
    }

    /**
     * 输出B
     */
    public synchronized void printB() {
        // 准备好再输出A
        num = 2;
        notifyAll();
    }

    /**
     * 准备输出A
     */
    public synchronized void waitForPrintA() throws InterruptedException {
        while (num != 1) {
            wait();
        }
    }

    /**
     * 准备输出B
     */
    public synchronized void waitForPrintB() throws InterruptedException {
        while (num != 2) {
            wait();
        }
    }
}

class PrintA implements Runnable {
    private Letter letter;

    public PrintA(Letter c) {
        letter = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("A ");
                TimeUnit.MILLISECONDS.sleep(200);

                letter.printA();
                letter.waitForPrintB();
            }
        } catch (InterruptedException e) {
            print("通过中断退出输出A");
        }
        print("完成输出A任务");
    }
}

class PrintB implements Runnable {
    private Letter letter;

    public PrintB(Letter c) {
        letter = c;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                letter.waitForPrintA();

                printnb("B ");
                TimeUnit.MILLISECONDS.sleep(200);

                letter.printB();
            }
        } catch (InterruptedException e) {
            print("通过中断退出输出B");
        }
        print("完成输出B任务");
    }
}

/**
 * 线程顺序执行
 *
 * @author Jef
 * @date 2022/2/20
 */
public class ThreadSequentialExecution {
    public static void main(String[] args) throws Exception {
        Letter letter = new Letter();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new PrintA(letter));
        exec.execute(new PrintB(letter));
        // while循环遍历
        TimeUnit.SECONDS.sleep(2);
        // 中断所有任务
        exec.shutdownNow();
    }
}