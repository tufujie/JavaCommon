package com.jef.algorithm;

/**
 * 计算储水量
 *
 * @author Jef
 * @date 2023/7/7
 */
public class WaterSave {

    public static void main(String[] args) {
        int[] arr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
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
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, arr[j]);
            }
            for (int j = i; j < arr.length; j++) {
                rightMax = Math.max(rightMax, arr[j]);
            }
            count += Math.min(leftMax, rightMax) - arr[i];
        }
        return count;
    }

    public static int countWaterV2(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int count = 0;
        int[] leftMaxArr = new int[arr.length], rightMaxArr = new int[arr.length];
        leftMaxArr[0] = arr[0];
        rightMaxArr[arr.length - 1] = arr[arr.length - 1];
        for (int i = 1; i < arr.length; i++) {
            leftMaxArr[i] = Math.max(leftMaxArr[i - 1], arr[i]);
        }
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMaxArr[i] = Math.max(rightMaxArr[i + 1], arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            count += Math.min(leftMaxArr[i], rightMaxArr[i]) - arr[i];
        }
        return count;
    }
}