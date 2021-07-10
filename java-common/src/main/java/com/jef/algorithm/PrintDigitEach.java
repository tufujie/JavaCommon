package com.jef.algorithm;

/**
 * 从个位开始打印数(换行)，可用场景，把数值从最后面一个框开始填写，依次填入框中，可用于打印金额操作
 */
public class PrintDigitEach {
    public static void main(String[] args) {
        printDigitEachJef(12304566);
        System.out.println("----------");
        printDigitEach(12340566);
    }

    /**
     * Jef的实现，未使用递归，方式功能已经实现
     */
    private static void printDigitEachJef(Integer digit) {
        if(digit < 0) {
            System.out.println("请输入非负整数");
        } else {
            System.out.println(digit % 10);
            while (digit / 10 >= 1) {
                digit /= 10;
                System.out.println(digit % 10);
            }
        }
    }

    /**
     * 优化方法，采用递归
     */
    private static void printDigitEach(Integer digit) {
        if(digit < 0) {
            System.out.println("请输入非负整数");
        } else {
            System.out.println(digit % 10);
            if (digit >= 10)
                printDigitEach(digit / 10);
        }
    }

}
