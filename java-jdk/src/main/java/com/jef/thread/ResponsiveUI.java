package com.jef.thread;

/**
 * 响应式UI
 *
 * @author Jef
 * @date 2022/2/19
 */
public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    @Override
    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    public static void main(String[] args) throws Exception {
        new ResponsiveUI();
        System.in.read();
        // 显示进度
        System.out.println(d);
    }
}