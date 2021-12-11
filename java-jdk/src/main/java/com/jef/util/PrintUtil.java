package com.jef.util;

import com.jef.constant.BasicConstant;

/**
 * @author Jef
 * @date 2021/4/13
 */
public class PrintUtil {
    /**
     * 循环打印数组
     */
    public static void printArray(Integer[] array) {
        for (Integer a : array) {
            System.out.println(a);
        }
    }

    /**
     * 打印一行分隔符
     */
    public static void printSplitLine() {
        System.out.println(BasicConstant.LINE_SPLIT);
    }

    /**
     * 换行
     */
    public static void changeLine() {
        System.out.println();
    }

    /**
     * 动态输入内容
     *
     * @param text
     */
    public static void outPrint(String text) {
        System.out.println(text);
    }

    /**
     * 格式化输出并换行
     *
     * @param format 代码替换内容的字符串
     * @param args   替换的内容
     * @author Jef
     * @date 2021/12/11
     */
    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
        System.out.println();
    }

}