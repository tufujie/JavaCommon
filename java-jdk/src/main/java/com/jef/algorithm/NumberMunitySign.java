package com.jef.algorithm;

/**
 * 数组元素积的符号
 * @author Jef
 * @date 2021/4/17
 */
public class NumberMunitySign {
    public static void main(String[] args) {
        int[] nums = {41,65,14,80,20,10,55,58,24,56,28,86,96,10,3,84,4,41,13,32,42,43,83,78,82,70,15,-41};
        System.out.println(new NumberMunitySign().arraySign(nums));
        int[] numsTwo = {9,72,34,29,-49,-22,-77,-17,-66,-75,-44,-30,-24};
        System.out.println(new NumberMunitySign().arraySign(numsTwo));
    }

    /**
     * 个人解法
     * 最优解
     * @author Jef
     * @date 2021/4/17
     * @param nums
     * @return int
     */
    public int arraySign(int[] nums) {
        // 默认是正数
        int result = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 如果是0就返回0
                return 0;
            } else if (nums[i] < 0) {
                // 如果是负数乘以-1，因为任何数乘以正数符号不变，所以正数无需处理
                result *= -1;
            }
        }
        return result;
    }
}