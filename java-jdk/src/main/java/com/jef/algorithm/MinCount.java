package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class MinCount {

    public static void main(String[] args) {
        System.out.println(new MinCount().minCount(new int[]{4,2,1}));
        System.out.println(new MinCount().minCount(new int[]{2,3,10}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param coins
     * @return int
     */
    public int minCount(int[] coins) {
        int minCount = 0;
        for (int i = 0; i < coins.length; i++) {
            int num = coins[i];
            while (num > 0) {
                ++minCount;
                int subNum = num % 2 == 0 ? 2 : 1;
                num -= subNum;
            }
        }
        return minCount;
    }

}