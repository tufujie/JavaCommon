package com.jef.algorithm.oftenUse;

import com.jef.util.PrintUtil;
import com.jef.util.RandomUtil;

/**
 * 数组下标
 *
 * @author Jef
 * @date 2021/12/21
 */
public class ArrayIndex {

    /**
     * 统计每个字母出现的次数
     *
     * @param strArray
     * @return void
     * @author Jef
     * @date 2021/12/21
     */
    public static void calcChar(char[] strArray) {
        int[] arr = new int[48];
        for (char str : strArray) {
            arr[str - 97]++;
        }
        String[] letterArray = RandomUtil.LETTER_CHAR.split("");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 0) {
                PrintUtil.printf("字母%s个数%s", letterArray[i], arr[i]);
            }
        }
    }
}