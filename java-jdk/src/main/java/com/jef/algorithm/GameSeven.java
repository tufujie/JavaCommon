package com.jef.algorithm;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 含7或者7的倍数
 *
 * @author Jef
 * @date 2022/5/14
 */
public class GameSeven {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arr = str.split(" ");
        // 几个人玩游戏
        int length = arr.length;
        int[] intArr = new int[length];
        // 总共喊“过”的次数
        int sum = 0;
        for (int i = 0; i < length; i++) {
            int num = Integer.parseInt(arr[i]);
            sum += num;
        }
        boolean flag = true;
        int number = 0;
        HashMap<Integer, Integer> indexNumMap = new HashMap<>();
        int totalNum = 0;
        while (flag) {
            for (int i = 0; i < length; i++) {
                ++number;
                if (number % 7 == 0 || String.valueOf(number).contains("7")) {
                    int num = 1;
                    if (indexNumMap.containsKey(i)) {
                        num = indexNumMap.get(i) + 1;
                    }
                    indexNumMap.put(i, num);
                    ++totalNum;
                    if (totalNum >= sum) {
                        flag = false;
                        break;
                    }
                }
            }
        }
        String result = "";
        for (int i = 0; i < length; i++) {
            Integer num = indexNumMap.get(i);
            if (num == null) {
                num = 0;
            }
            result += num;
            if (i != length - 1) {
                result += " ";
            }
        }
        System.out.println(result);
    }

}