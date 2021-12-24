package com.jef.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class ReplaceDigits {

    public static void main(String[] args) {
        System.out.println(new ReplaceDigits().replaceDigits("a1c1e1"));
        System.out.println(new ReplaceDigits().replaceDigits("a1b2c3d4e"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public String replaceDigits(String s) {
        String[] strings = s.split("");
        String letter = "abcdefghijklmnopqrstuvwxyz";
        String[] letterArray = letter.split("");
        Map<String, Integer> charMap = new HashMap<>();
        for (int i = 0; i < letterArray.length; i++) {
            charMap.put(letterArray[i], i);
        }
        for (int i = 0; i < strings.length - 1; i += 2) {
            String string = strings[i];
            Integer num = Integer.valueOf(strings[i + 1]);
            Integer index = charMap.get(string) + num;
            String replaceString = letterArray[index];
            strings[i + 1] = replaceString;
        }
        s = "";
        for (String s1 : strings) {
            s += s1;
        }
        return s;
    }

}