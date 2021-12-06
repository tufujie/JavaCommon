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
    public static void printLineSplit() {
        System.out.println(BasicConstant.LINE_SPLIT);
    }

}