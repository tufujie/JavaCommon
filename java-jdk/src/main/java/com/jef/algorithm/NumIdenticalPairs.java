package com.jef.algorithm;

/**
 * 好数对的数目
 *
 * @author Jef
 * @date 2021/5/30
 */
public class NumIdenticalPairs {

    public static void main(String[] args) {
        System.out.println(new NumIdenticalPairs().numIdenticalPairs(new int[]{1,2,3,1,1,3}));
        System.out.println(new NumIdenticalPairs().numIdenticalPairs(new int[]{1,1,1,1}));
        System.out.println(new NumIdenticalPairs().numIdenticalPairs(new int[]{1,2,3}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    ++count;
                }
            }
        }
        return count;
    }

}