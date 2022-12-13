package com.jef.extendtest;

/**
 * @author Administrator
 * @date 2022/9/24
 */
public class BroadcastReceiver {
    private String content;

    public void onReceive(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(String content) {
                System.out.println("testAdd" + content);
            }
        };
        broadcastReceiver.onReceive("12313");
    }

}