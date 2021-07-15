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
}