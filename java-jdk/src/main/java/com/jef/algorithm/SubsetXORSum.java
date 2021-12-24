package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class SubsetXORSum {

    public static void main(String[] args) {
        System.out.println(new SubsetXORSum().subsetXORSum(new int[]{1,3}));
        System.out.println(new SubsetXORSum().subsetXORSum(new int[]{5,1,6}));
    }

    int res = 0;

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */

    public int subsetXORSum(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        dfs(nums, 0, 0);
        return res;
    }

    // i：表示来到第i个位置
    public void dfs(int[] nums, int i ,int xorSum) {
        if (i == nums.length) {
            res += xorSum;
            return;
        }
        // 当前位置要
        dfs(nums, i + 1, xorSum ^ nums[i]);
        // 当前位置不要
        dfs(nums, i + 1, xorSum);
    }

}