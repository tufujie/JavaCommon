package com.jef.algorithm.dp;

/**
 * 最小路径数字和-动态规划算法
 * 给定⼀个包含⾮负整数的 m x n ⽹格，请找出⼀条从左上⻆到右下⻆的路径，使得路径上的数字总和为最⼩。
 *
 * @author Jef
 * @date 2021/12/26
 */
public class MinStepNumber {

    /**
     * 找出最短路径
     *
     * @param arr 网格数组
     * @return int
     * @author Jef
     * @date 2021/12/26
     */
    public static int getLeastNumber(int[][] arr) {
        // 获取行数和列数
        int m = arr.length, n = arr[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        // 初始化第一行第一列
        dp[0][0] = arr[0][0];
        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + arr[0][j];
        }
        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + arr[i][0];
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + arr[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

}