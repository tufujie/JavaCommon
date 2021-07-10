package com.jef.thread;

public class BasicThreads {
    public static void main(String[] args) {
        basicThreadError();
        System.out.println("等待LiftOff");
    }

    /**
     * 基础但经典的多线程使用方式
     */
    public static void basicThreadError() {
        for(int i = 0; i < 5; i++) {
            new Thread(new ThreadYieldTest()).start();
        }
    }
}
