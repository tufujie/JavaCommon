package com.jef.algorithm.dp;

/**
 * 唯一路径数-动态规划算法
 * ⼀个机器⼈位于⼀个 m x n ⽹格的左上⻆ （起始点在下图中标记为“Start” ）。
 * 机器⼈每次只能向下或者向右移动⼀步。机器⼈试图达到⽹格的右下⻆（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * @author Jef
 * @date 2021/12/26
 */
public class UniquePath {

    /**
     * 获取路径数
     *
     * @param m 行数
     * @param n 列数
     * @author Jef
     * @date 2021/12/26
     */
    public static int getUniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 优化获取路径数
     *
     * @param m 行数
     * @param n 列数
     * @author Jef
     * @date 2021/12/26
     */
    public static int getUniquePathsOptimization(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[] dp = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < m; i++) {
            // 第 i 行第 0 列的值
            dp[0] = 1;
            for (int j = 1; j < n; j++) {
                // 公式： dp[i] = dp[i - 1] + dp[i]
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        return dp[n - 1];
    }

}