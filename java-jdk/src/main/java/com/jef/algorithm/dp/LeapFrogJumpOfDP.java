package com.jef.algorithm.dp;

/**
 * 青蛙跳-动态规划算法
 * ⼀只⻘蛙⼀次可以跳上1级台阶，也可以跳上2级。求该⻘蛙跳上⼀个n级的台阶总共有多少种跳法。
 *
 * @author Jef
 * @date 2021/12/24
 */
public class LeapFrogJumpOfDP {

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