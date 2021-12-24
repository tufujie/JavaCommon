package com.jef.algorithm;

/**
 * 回文数
 * @author Jef
 * @date 2021/4/18
 */
public class IsPalindrome {
    public static void main(String[] args) {
        System.out.println(new IsPalindrome().isPalindrome(123));
        System.out.println(new IsPalindrome().isPalindrome(121));
        System.out.println(new IsPalindrome().isPalindrome(-121));
    }

    /**
     * 判断是否是回文数
     * 个人解法
     * @author Jef
     * @date 2021/4/18
     * @param x
     * @return boolean
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        } else {
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
            for (int k = 0; k < n; k++) {
                if (array[k] != array[n - k - 1]) {
                    return false;
                }
            }
            return true;
        }
    }

}