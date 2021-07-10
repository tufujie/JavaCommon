package com.jef.algorithm;

/**
 * Created by Jef on 2017-08-25.
 * 大数阶乘
 */
public class FactorialOfBigNumber {
    public static void main(String[] args) {
        System.out.println(factorialOfBigNumber(1000000000));
    }


    public static Long factorialOfBigNumber(Integer n) {
        // 个人实现，里面有些问题
        Long a = 1L;
        if (n <= 1) {
            return 1L;
        } else {
            while (n >= 1) {
                a *= n;
                n--;
                System.out.println(a);
            }
            return a;
        }
    }

    /**
     * 标准写法
     * 递归
     */
    public static long factorial(int n) {
        if (n == 1) {
            return 1L;
        } else {
            return n * factorial(n - 1);
        }
    }

}
