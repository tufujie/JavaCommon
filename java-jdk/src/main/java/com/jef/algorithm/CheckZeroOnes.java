package com.jef.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 哪种连续子字符串更长
 *
 * @author Jef
 * @date 2021/5/30
 */
public class CheckZeroOnes {

    public static void main(String[] args) {
        System.out.println(new CheckZeroOnes().checkZeroOnes("1101"));
        System.out.println(new CheckZeroOnes().checkZeroOnes("111000"));
        System.out.println(new CheckZeroOnes().checkZeroOnes("110100010"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param s
     * @return int
     */
    public boolean checkZeroOnes(String s) {
        int oneNum = 0, zeroNum = 0, oneContinue = 0, zeroContinue = 0;
        String[] stringArray = s.split("");
        Map<Integer, Integer> oneContinueMap = new HashMap<>(), zeroContinueMap = new HashMap<>();
        for (int i = 0; i < stringArray.length; i++) {
            if (stringArray[i].equals("1")) {
                ++oneNum;
                if (zeroNum > 0) {
                    zeroContinueMap.put(zeroContinue++, zeroNum);
                }
                if (i == stringArray.length - 1) {
                    oneContinueMap.put(oneContinue++, oneNum);
                }
                zeroNum = 0;
            } else {
                ++zeroNum;
                if (oneNum > 0) {
                    oneContinueMap.put(oneContinue++, oneNum);
                }
                if (i == stringArray.length - 1) {
                    zeroContinueMap.put(zeroContinue++, zeroNum);
                }
                oneNum = 0;
            }
        }
        oneNum = 0;
        zeroNum = 0;
        for (Integer oneNumTemp : oneContinueMap.values()) {
            if (oneNumTemp > oneNum) {
                oneNum = oneNumTemp;
            }
        }
        for (Integer zeroNumTemp : zeroContinueMap.values()) {
            if (zeroNumTemp > zeroNum) {
                zeroNum = zeroNumTemp;
            }
        }
        return oneNum > zeroNum;
    }

}