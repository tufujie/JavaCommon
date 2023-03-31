package com.jef.algorithm;

/**
 * @author Jef
 * @date 2020/4/6
 */
public class MaxGet {

    /**
     * 获取三个数的最大数
     */
    public static int getMaxOfThree( int a, int b, int c )
    {
        return a > b ? (a > c ? a : c) : (b > c ? b : c);
    }

    /**
     * 获取数组中的最大值
     */
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}