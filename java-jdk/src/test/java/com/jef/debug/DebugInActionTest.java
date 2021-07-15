package com.jef.debug;

import org.junit.Test;

/**
 * debug实战
 * @author Jef
 * @date 2020/12/21
 */
public class DebugInActionTest {

    public static void main(String[] args) {
        System.out.println("test");
        f1();
    }

    public static void f1(){
        System.out.println("One");
        System.out.println("Two");
        System.out.println("Three");
    }

    public static void f2(){
        for (char ch = 0; ch < 128; ch++) {
            if (Character.isUpperCase(ch)) {
                System.out.println("value:" + (int)ch + " character:"+ch);
            }
        }
        f1();
    }

    @Test
    public void dropFrameDebug() {
        int i = 99;
        method1(i);
    }

    private void method1(int i) {
        System.out.println("method1:" + i++);
        method2(i);
    }

    private void method2(int j) {
        j++;
        System.out.println("method2:" + j);
    }

    @Test
    public void multiThreadTest() {
        new Thread(() -> {
            System.out.println("1.离离原上草");
        }, "原上草").start();

        new Thread(() -> {
            System.out.println("2.一岁一枯荣");
        }, "一枯荣").start();

        System.out.println("3.野火烧不尽");
        System.out.println("4.春风吹又生");
    }

}