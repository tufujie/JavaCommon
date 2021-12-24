package com.jef.algorithm;

/**
 * 按既定顺序创建目标数组
 *
 * @author Jef
 * @date 2021/5/30
 */
public class CreateTargetArray {

    public static void main(String[] args) {
        System.out.println(new CreateTargetArray().createTargetArray(new int[]{4,2,4,3,2}, new int[]{0,0,1,3,1}));
        System.out.println(new CreateTargetArray().createTargetArray(new int[]{0,1,2,3,4}, new int[]{0,1,2,2,1}));
        System.out.println(new CreateTargetArray().createTargetArray(new int[]{1,2,3,4,0}, new int[]{0,1,2,3,0}));
        System.out.println(new CreateTargetArray().createTargetArray(new int[]{1}, new int[]{0}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] array = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            array[i] = -1;
        }
        for (int i = 0; i < nums.length; i++) {
            int indexIndex = index[i], val = nums[i];
            if (array[indexIndex] == -1) {
                array[indexIndex] = val;
            } else {
                // 往后面推值
                for (int j = nums.length - 1; j > indexIndex ; j--) {
                    array[j] = array[j - 1];
                }
                array[indexIndex] = val;
            }
        }
        return array;
    }

}