package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class RunningSum {

    public static void main(String[] args) {
        System.out.println(new RunningSum().runningSum(new int[]{1,2,3,4}));
        System.out.println(new RunningSum().runningSum(new int[]{1,1,1,1,1}));
        System.out.println(new RunningSum().runningSum(new int[]{3,1,2,10,1}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */
    public int[] runningSum(int[] nums) {
        int[] resultSumNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int addTemp = 0;
            if (i > 0) {
                addTemp = resultSumNums[i-1];
            }
            resultSumNums[i] = nums[i] + addTemp;
        }
        return resultSumNums;
    }

}