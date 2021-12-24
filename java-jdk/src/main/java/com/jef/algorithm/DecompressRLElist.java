package com.jef.algorithm;

/**
 * 解压缩编码列表
 *
 * @author Jef
 * @date 2021/5/30
 */
public class DecompressRLElist {

    public static void main(String[] args) {
        System.out.println(new DecompressRLElist().decompressRLElist(new int[]{1,2,3,4}));
        System.out.println(new DecompressRLElist().decompressRLElist(new int[]{1,1,2,3}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param nums
     * @return int
     */
    public int[] decompressRLElist(int[] nums) {
        int size = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            size += nums[i];
        }
        int[] resultArr = new int[size];
        int tempIndex = 0;
        for (int i = 0; 2 * i + 1 < nums.length; i++) {
            int tempNum = nums[2 * i], val = nums[2 * i + 1];
            for (int j = 0; j < tempNum; j++) {
                resultArr[tempIndex++] = val;
            }
        }
        return resultArr;
    }

}