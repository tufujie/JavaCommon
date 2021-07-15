package com.jef.other;


public class StaticAndNoStatic {
    private int instanceVar = 0;
    private static int staticVar = 0;
    public StaticAndNoStatic() {
        instanceVar++;
        staticVar++;
        System.out.println("instanceVar=" + instanceVar + " staticVar=" + staticVar);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new StaticAndNoStatic();
        }
    }
}
