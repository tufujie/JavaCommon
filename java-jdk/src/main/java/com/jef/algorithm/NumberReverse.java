package com.jef.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 整数反转
 * @author Jef
 * @date 2021/4/17
 */
public class NumberReverse {
    public static void main(String[] args) {
        System.out.println(new NumberReverse().reverse(123));
        System.out.println(new NumberReverse().reverse(-123));
        System.out.println(new NumberReverse().reverse(120));
        System.out.println(new NumberReverse().reverse(0));
        System.out.println(new NumberReverse().reverse(1534236469));
        System.out.println(new NumberReverse().reverse(-2147483648));
    }


    /**
     * 个人解法
     * @author Jef
     * @date 2021/4/17
     * @param x
     * @return int
     */
    public int reverse(int x) {
        if (x < (Integer.MAX_VALUE * -1)) {
            return 0;
        }
        boolean lessThanZero = false;
        if (x < 0) {
            lessThanZero = true;
            x = x * -1;
        }
        // 先判断数字是几位数字
        int n = 1, y = x;
        while (true) {
           y = y / 10;
           if (y < 1) {
               break;
           }
           n++;
        }
        int[] array = new int[n];
        int temp, i = 0, m = n;
        // 获取数组
        while (m > 0) {
            int tempTwo = 1;
            for (int j = 0; j < m - 1; j++) {
                tempTwo = tempTwo* 10;
            }
            temp = x / tempTwo;
            temp = temp % 10;
            m--;
            array[i++] = temp;
        }
        // 反转数组
        Set<Integer> set = new HashSet<>();
        for (int k = 0; k < n; k++) {
            int reverseIndex = (n - k - 1);
            if (k != reverseIndex && !set.contains(k) && !set.contains(reverseIndex)) {
                int tempK = array[k];
                array[k] = array[reverseIndex];
                array[reverseIndex] = tempK;
                set.add(k);
                set.add(reverseIndex);
            }
        }
        // 获取数组的值
        long result = 0;
        for (int k = 0; k < n; k++) {
            long tempTwo = 1;
            for (int j = k; j < n - 1; j++) {
                tempTwo = tempTwo* 10;
            }
            result += array[k] * tempTwo;
        }
        if (result > Integer.MAX_VALUE || result < (Integer.MAX_VALUE * -1)) {
            return 0;
        }
        if (lessThanZero) {
            result *= -1;
        }
        return (int) result;
    }

}