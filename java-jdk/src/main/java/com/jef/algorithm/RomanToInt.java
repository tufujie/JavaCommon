package com.jef.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 罗马数字转整数
 * @author Jef
 * @date 2021/4/18
 */
public class RomanToInt {

    public static void main(String[] args) {
        System.out.println(new RomanToInt().romanToInt("III"));
        System.out.println(new RomanToInt().romanToInt("IV"));
        System.out.println(new RomanToInt().romanToInt("IX"));
        System.out.println(new RomanToInt().romanToInt("LVIII"));
        System.out.println(new RomanToInt().romanToInt("MCMXCIV"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/4/18
     * @param s
     * @return int
     */
    public int romanToInt(String s) {
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("I", 1);
        stringIntegerMap.put("V", 5);
        stringIntegerMap.put("X", 10);
        stringIntegerMap.put("L", 50);
        stringIntegerMap.put("C", 100);
        stringIntegerMap.put("D", 500);
        stringIntegerMap.put("M", 1000);
        String[] tempArray = s.split("");
        int result = 0;
        for (int i = 0 ; i < tempArray.length; i++) {
            int tempNum = stringIntegerMap.get(tempArray[i]);
            if (i + 1 < tempArray.length && tempNum < stringIntegerMap.get(tempArray[i + 1])) {
                tempNum = stringIntegerMap.get(tempArray[i + 1]) - tempNum;
                i++;
            }
            result += tempNum;
        }
        return result;
    }
}