package com.jef.thread;

public class BasicThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;
    public BasicThread() {
        // 名字就是数字的字符串格式
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown < 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            new BasicThread();
        }
    }
}
