package com.jef.algorithm.dp;

/**
 * 转换单词最小数-动态规划算法
 * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使⽤的最少操作数 。
 * 你可以对⼀个单词进⾏如下三种操作：
 * 插⼊⼀个字符、删除⼀个字符、替换⼀个字符
 *
 * @author Jef
 * @date 2021/12/26
 */
public class ChangeWordsMinStep {
    /**
     * 获取转换单词最小数
     *
     * @return int
     * @author Jef
     * @date 2021/12/26
     */
    public static int getChangeWordsMinStepNumber(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 初始化第一行
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        // 初始化第一列
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        // 推导出 dp[m][n]
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /*
                     * （1）、如果把字符 word1[i] 替换成与 word2[j] 相等，则有 dp[i] [j] = dp[i-1] [j-1] + 1;
                     * （2）、如果在字符串 word1末尾插⼊⼀个与 word2[j] 相等的字符，则有 dp[i] [j] = dp[i] [j-1] + 1;
                     * （3）、如果把字符 word1[i] 删除，则有 dp[i] [j] = dp[i-1] [j] + 1;
                     * */
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 优化获取转换单词最小数
     *
     * @return int
     * @author Jef
     * @date 2021/12/26
     */
    public static int getChangeWordsMinStepNumberOptimization(String word1, String word2) {
        if (word1.equals(word2)) {
            return 0;
        }
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        // dp[0...n]的初始值
        for (int j = 1; j <= n; j++) {
            dp[j] = j;
        }
        // 推导出 dp[n]
        for (int i = 1; i <= m; i++) {
            int temp = dp[0];
            // 相当于初始化
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                // pre 相当于之前的 dp[i - 1][j - 1]
                int pre = temp;
                temp = dp[j];
                // 如果 word1[i] 与 word2[j] 相等。第 i 个字符对应下标是 i-1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), pre) + 1;
                }
            }
        }
        return dp[n];
    }
}