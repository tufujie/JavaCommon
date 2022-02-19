package com.jef.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable {
    private int i = 0;
    public int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        // 想要达到每次都加2、最终是偶数的效果
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println("产生了奇数=" + val);
                System.exit(0);
            } else {
                System.out.println(val + " 是偶数");
            }
        }
    }

}
