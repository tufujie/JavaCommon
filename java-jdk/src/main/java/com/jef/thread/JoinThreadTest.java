package com.jef.thread;

import com.jef.business.BusinessDemo;
import com.jef.util.PrintUtil;
import com.jef.util.ThreadUtil;

/**
 * 加入一个线程
 *
 * @author Jef
 * @date 2022/2/19
 */
class Sleeper extends Thread {
    private int sleepTime;

    public Sleeper(String name, int sleepTime) {
        super(name);
        this.sleepTime = sleepTime;
        start();
    }

    @Override
    public void run() {
        try {
            sleep(sleepTime);
        } catch (InterruptedException e) {
            ThreadUtil.printCurrentThreadIsInterrupted();
            return;
        }
        PrintUtil.printf(getName() + " 被唤起");
        BusinessDemo.taskHasReturn("Thread.join()");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try {
            /*
            一个线程可以在其他线程之上调用join()方法，其效果是等待一段时间直到第二个线程结束才继续执行。
            如果某个线程在另一个线程t上调用t.join()，此线程将被挂起，直到目标线程t结束才恢复(即t.isAlive()返回为假)。
            所以这个例子会先执行sleeper线程任务，然后执行joiner线程任务
             */
            sleeper.join();

        } catch (InterruptedException e) {
            ThreadUtil.printCurrentThreadIsInterrupted();
        }
        ThreadUtil.printThreadInfo(sleeper);
        PrintUtil.print(getName() + " 加入线程完成");
        BusinessDemo.taskHasReturn("Thread.join()");
    }
}

public class JoinThreadTest {

    public static void main(String[] args) {
        Sleeper jef1 = new Sleeper("Jef 1", 1500), jef2 = new Sleeper("Jef 2", 1500);
        Joiner jef3 = new Joiner("Jef 3", jef1), jef4 = new Joiner("Jef 4", jef2);
        /*
        当另-一个线程在该线程上调用interrupt()时，将给该线程设定一个标志，表明该线程已经被中断。然而，异常被捕获时将清理这个标志，所以在catch子句中，在异常被捕获的时候这个标志总是为假。
        除异常之外，这个标志还可用于其他情况、比如线程可能会检杏其中断状太
         */
        jef2.interrupt();
    }
}
