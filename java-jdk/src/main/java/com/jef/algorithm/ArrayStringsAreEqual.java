package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class ArrayStringsAreEqual {

    public static void main(String[] args) {
        System.out.println(new ArrayStringsAreEqual().arrayStringsAreEqual(new String[]{"ab", "c"}, new String[]{"a", "bc"}));
        System.out.println(new ArrayStringsAreEqual().arrayStringsAreEqual(new String[]{"a", "cb"}, new String[]{"ab", "c"}));
        System.out.println(new ArrayStringsAreEqual().arrayStringsAreEqual(new String[]{"abc", "d", "defg"}, new String[]{"abcddefg"}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        String a = "", b = "";
        for (String word : word1) {
            a += word;
        }
        for (String word : word2) {
            b += word;
        }
        return a.equals(b);
    }

}