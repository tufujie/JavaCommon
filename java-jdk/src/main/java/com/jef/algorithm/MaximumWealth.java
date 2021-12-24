package com.jef.algorithm;

/**
 * 最富有客户的资产总量
 *
 * @author Jef
 * @date 2021/5/30
 */
public class MaximumWealth {

    public static void main(String[] args) {
        System.out.println(new MaximumWealth().maximumWealth(new int[][]{{1,2,3}, {3,2,1}}));
        System.out.println(new MaximumWealth().maximumWealth(new int[][]{{1,5}, {7,3}, {3,5}}));
        System.out.println(new MaximumWealth().maximumWealth(new int[][]{{2,8,7}, {7,1,3}, {1,9,5}}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int maximumWealth(int[][] accounts) {
        int max = 0;
        for (int i = 0; i < accounts.length; i++) {
            int[] arrayTemp = accounts[i];
            int maxTemp = 0;
            for (int j = 0; j < arrayTemp.length; j++) {
                maxTemp += arrayTemp[j];
            }
            if (maxTemp > max) {
                max = maxTemp;
            }
        }
        return max;
    }
}