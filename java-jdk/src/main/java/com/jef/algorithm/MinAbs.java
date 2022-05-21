package com.jef.algorithm;

import java.util.Scanner;

/**
 * 最小绝对值
 *
 * @author Jef
 * @date 2022/5/21
 */
public class MinAbs {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        String[] strArray = str.split(" ");
        int min = Math.abs(Integer.valueOf(strArray[0]) + Integer.valueOf(strArray[1]));
        for (int j = 0; j < strArray.length - 1; j++) {
            for (int k = j + 1; k < strArray.length; k++) {
                int tempNum = Math.abs(Integer.valueOf(strArray[j]) + Integer.valueOf(strArray[k]));
                if (tempNum < min) {
                    min = tempNum;
                }
            }
        }
        System.out.println(min);
    }

}