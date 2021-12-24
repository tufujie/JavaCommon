package com.jef.algorithm;

/**
 * 重新排列数组
 *
 * @author Jef
 * @date 2021/5/30
 */
public class Shuffle {

    public static void main(String[] args) {
        System.out.println(new Shuffle().shuffle(new int[]{2,5,1,3,4,7}, 3));
        System.out.println(new Shuffle().shuffle(new int[]{1,2,3,4,4,3,2,1}, 4));
        System.out.println(new Shuffle().shuffle(new int[]{1,1,2,2}, 2));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */
    public int[] shuffle(int[] nums, int n) {
        int[] resultNum = new int[2 * n];
        int j = 0;
        for (int i = 0; i < nums.length / 2; i++) {
            resultNum[j++] = nums[i];
            resultNum[j++] = nums[i + n];
        }
        return resultNum;
    }
}