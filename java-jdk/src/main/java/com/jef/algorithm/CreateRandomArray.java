package com.jef.algorithm;

import java.util.Arrays;

/**
 * 数据里有{1,2,3,4,5,6,7,8,9}，请随机打乱顺序，生成一个新的数组
 *
 * @author Jef
 * @date 2022/6/11
 */
public class CreateRandomArray {

    // 随机打乱
    public static int[] srand(int[] a) {
        int[] b = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            // 随机获取下标
            int tmp = (int) (Math.random() * (a.length - i));//随机数：[0，a.length-i)
            b[i] = a[tmp];
            // 将此时a[tmp]的下标移动到靠后的位置
            int change = a[a.length - i - 1];
            a[a.length - i - 1] = a[tmp];
            a[tmp] = change;
        }
        return b;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(srand(a)));
    }

}