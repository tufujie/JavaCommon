package com.jef.inner;

public class Outer {
    private static int a;
    private int b;
    public class Inner {
        public void print() {
            System.out.println(a);
            System.out.println(b);
        }
    }
}