package com.jef.algorithm;

/**
 * 统计一致字符串的数目
 *
 * @author Jef
 * @date 2021/5/30
 */
public class CountConsistentStrings {

    public static void main(String[] args) {
        System.out.println(new CountConsistentStrings().countConsistentStrings("ab", new String[]{"ad","bd","aaab","baa","badab"}));
        System.out.println(new CountConsistentStrings().countConsistentStrings("abc", new String[]{"a","b","c","ab","ac","bc","abc"}));
        System.out.println(new CountConsistentStrings().countConsistentStrings("cad", new String[]{"cc","acd","b","ba","bac","bad","ac","d"}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int countConsistentStrings(String allowed, String[] words) {
        int num = 0;
        for (String string : words) {
            String[] stringTemp = string.split("");
            boolean add = true;
            for (String stringTempTemp : stringTemp) {
                add = allowed.contains(stringTempTemp);
                if (!add) {
                    break;
                }
            }
            num += add ? 1 : 0;
        }
        return num;
    }

}