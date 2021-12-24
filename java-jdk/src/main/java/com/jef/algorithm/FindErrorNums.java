package com.jef.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class FindErrorNums {

    public static void main(String[] args) {
        System.out.println(new FindErrorNums().findErrorNums(new int[]{1, 2, 2, 4}));
        System.out.println(new FindErrorNums().findErrorNums(new int[]{1, 1}));
        System.out.println(new FindErrorNums().findErrorNums(new int[]{2, 2}));
    }

    /**
     * 个人解法
     *
     * @param nums
     * @return int
     * @author Jef
     * @date 2021/5/30
     */
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        // 找出错误的方法1
/*        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    result[0] = nums[i];
                    break;
                }
            }
        }*/
        // 找出错误的方法2
        Set<Integer> numSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!numSet.add(nums[i])) {
                result[0] = nums[i];
            }
        }
        // 找出缺失的方法1
/*        for (int i = 0; i < nums.length; i++) {
            boolean notFind = true;
            for (int j = 0; j < nums.length; j++) {
                if (i + 1 == nums[j]) {
                    notFind = false;
                    break;
                }
            }
            if (notFind) {
                result[1] = i + 1;
                break;
            }
        }*/
        // 找出缺失的方法2
        Set<Integer> existInteger = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            existInteger.add(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!existInteger.contains(i + 1)) {
                result[1] = i + 1;
                break;
            }
        }
        return result;
    }

}