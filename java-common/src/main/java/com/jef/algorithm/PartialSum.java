package com.jef.algorithm;

/**
 * @author Jef
 * @date 2020/4/6
 */
public class PartialSum {

    /**
     * 从1开始的连续数三次方求和
     * @author Jef
     * @date 2020/4/6
     * @param n 整数的个数
     * @return int
     */
    public static int sum(int n) {
        int particalSum = 0;
        for (int i = 0; i <= n; i++) {
            particalSum += i * i * i;
        }
        return particalSum;
    }
}