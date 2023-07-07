package com.jef.algorithm;

/**
 * 计算储水量
 *
 * @author Jef
 * @date 2023/7/7
 */
public class WaterSave {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2};
        System.out.println("v1=" + countWater(arr));
        System.out.println("v2=" + countWaterV2(arr));
    }

    public static int countWater(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, arr[j]);
            }
            for (int j = i; j < arr.length; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }
            count += Math.max(leftMax, rightMax) - arr[i];
        }
        return count;
    }

    public static int countWaterV2(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int count = 0;
        int[] leftMaxArr = new int[arr.length], rightMaxArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = 0; j < i; j++) {
                leftMaxArr[i] = Math.max(leftMax, arr[j]);
            }
            for (int j = i; j < arr.length; j++) {
                rightMaxArr[i] = Math.max(rightMax, arr[j]);
            }
        }
        for (int i = 0; i < arr.length; i++) {
            count += Math.max(leftMaxArr[i], rightMaxArr[i]) - arr[i];
        }
        return count;
    }
}