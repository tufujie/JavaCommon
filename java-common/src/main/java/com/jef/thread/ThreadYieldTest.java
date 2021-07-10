package com.jef.thread;

public class ThreadYieldTest implements Runnable {
    protected int countDown = 5;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public ThreadYieldTest() {

    }

    public ThreadYieldTest(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id +  "(" +(countDown > 0 ?  countDown + ")，" : "End!)\n") ;
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            // Thread.yield可以将CPU从一个线程转移给另一个线程
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadYieldTest()).start();
        }
    }
}
