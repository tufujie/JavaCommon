package com.jef.algorithm;

/**
 * 数组拓展
 *
 * @author Jef
 * @date 2020/6/21 0021
 */
public class ArrayExtend {

    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        // 开始拓展
        int[] newArr = new int[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
        System.out.println(arr);
    }

}