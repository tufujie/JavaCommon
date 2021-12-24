package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class SubtractProductAndSum {

    public static void main(String[] args) {
        System.out.println(new SubtractProductAndSum().subtractProductAndSum(234));
        System.out.println(new SubtractProductAndSum().subtractProductAndSum(4421));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param n
     * @return int
     */
    public int subtractProductAndSum(int n) {
        int add = 0, mul = 1;
        while (n > 0) {
            int digt = n % 10;
            n /= 10;
            add += digt;
            mul *= digt;
        }
        return mul - add;
    }

}