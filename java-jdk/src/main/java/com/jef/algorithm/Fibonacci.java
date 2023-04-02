package com.jef.algorithm;

/**
 * 斐波那契数列
 * ⼤家都知道斐波那契数列，现在要求输⼊⼀个整数n，请你输出斐波那契数列的第n项。
 * n<=39
 */
public class Fibonacci {

    /**
     * 采⽤迭代法：
     */
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int first = 1, second = 1, third = 0;
        for (int i = 3; i <= n; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 采⽤递归：
     */
    public static int fibonacci2(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fibonacci2(n - 2) + fibonacci2(n - 1);
    }

}