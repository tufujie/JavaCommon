package com.jef.algorithm;

/**
 * 有多少小于当前数字的数字
 *
 * @author Jef
 * @date 2021/5/30
 */
public class SmallerNumbersThanCurrent {

    public static void main(String[] args) {
        System.out.println(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{8,1,2,2,3}));
        System.out.println(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{6,5,4,8}));
        System.out.println(new SmallerNumbersThanCurrent().smallerNumbersThanCurrent(new int[]{7,7,7,7}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int num = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < nums[i] && i != j) {
                    num++;
                }
            }
            result[i] = num;
        }
        return result;
    }

}