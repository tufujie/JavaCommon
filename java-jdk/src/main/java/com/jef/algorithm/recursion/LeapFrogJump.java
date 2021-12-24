package com.jef.algorithm.recursion;

/**
 * ⼀只⻘蛙⼀次可以跳上1级台阶，也可以跳上2级。求该⻘蛙跳上⼀个n级的台阶总共有多少种跳法。
 *
 * @author Jef
 * @date 2021/12/24
 */
public class LeapFrogJump {

    /**
     * 跳法数
     * 《递归》算法
     * 1、递归函数功能
     *
     * @param n 台阶数
     * @return int 跳法数
     * @author Jef
     * @date 2021/12/24
     */
    public static int jumpKind(int n, int[] tempArr) {
        // 2、找出递归结束的条件
        if (n <= 2) {
            return n;
        }
        // 递归优化1. 考虑是否重复计算
        // 先判断有没计算过
        if (tempArr[n] != -1) {
            // 计算过，直接返回
            return tempArr[n];
        } else {
            // 3、找出函数的等价关系式
            // 没有计算过，递归计算，并且把结果保存到 tempArr 数组⾥
            tempArr[n] = jumpKind(n - 1, tempArr) + jumpKind(n - 2, tempArr);
            return tempArr[n];
        }
    }

    /**
     * 跳法数
     * 《递归》算法
     * 递归优化2. 考虑是否可以⾃底向上
     *
     * @param n 台阶数
     * @return int 跳法数
     * @author Jef
     * @date 2021/12/24
     */
    public static int jumpKindV2(int n) {
        if (n <= 2) {
            return n;
        }
        int f1 = 1;
        int f2 = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }
        return sum;
    }

    /**
     * 跳法数
     * 《动态规划》算法
     *
     * @param n 台阶数
     * @return int 跳法数
     * @author Jef
     * @date 2021/12/24
     */
    public static int jumpKindOfDp(int n) {
        if (n <= 2) {
            return n;
        }
        // 1、定义数组元素的含义：先创建⼀个数组来保存历史数据
        int[] dp = new int[n + 1];
        // 2、找出初始条件
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            // 3、找出函数的等价关系式：通过关系式来计算出 dp[n]
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        // 把最终结果返回
        return dp[n];
    }
}