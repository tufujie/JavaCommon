package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class NumberOfSteps {

    public static void main(String[] args) {
        System.out.println(new NumberOfSteps().numberOfSteps(14));
        System.out.println(new NumberOfSteps().numberOfSteps(8));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int numberOfSteps(int num) {
        int res = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num -= 1;
            }
            res++;
        }
        return res;
    }

}