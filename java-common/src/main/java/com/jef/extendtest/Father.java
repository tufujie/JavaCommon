package com.jef.extendtest;

/**
 * @author Jef
 * @date 2020/12/29
 */
public class Father {
    public static void main(String[] args) {
    }

    public void sayHello() {
        System.out.println("Hello");
    }

    public void sayHello(String name) {
        System.out.println("Hello" + " " + name);
    }
}