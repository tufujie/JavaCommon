package com.jef.algorithm;

import java.util.Scanner;

/**
 * 最大面积
 * 10,9,8,7,6,5,4,3,2,1
 * 1,2,3
 * 1,3,2
 * 3,2,5
 *
 * @author Jef
 * @date 2022/5/14
 */
public class MaxArea {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(",");
        int length = arr.length;
        Long maxArea = 0L;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                Long left = Long.parseLong(arr[i]), right = Long.parseLong(arr[j]);
                Long minTemp = Math.min(left, right);
                int sub = Math.abs(j - i);
                maxArea = Math.max(maxArea, minTemp * sub);
            }
        }
        System.out.println(maxArea);
    }

}