package com.jef.algorithm;

/**
 * 两数之和
 * @author Jef
 * @date 2021/4/17
 */
public class NumberAddEqual {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int[] result = new NumberAddEqual().twoSum(nums, 9);
        System.out.println(result);
    }

    /**
     * 个人解法
     * 最优解
     * @author Jef
     * @date 2021/4/17
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        // 遍历
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("not found");
    }
}