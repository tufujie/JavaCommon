package com.jef.algorithm;

/**
 * 最长公共前缀
 * @author Jef
 * @date 2021/4/18
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"dog","racecar","car"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{""}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"a"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"ab", "a"}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"", ""}));
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(new String[]{"c","acc","ccc"}));
    }

    /**
     * 个人解法
     * 从第一个字符串的任意位置截取任意一个字符串子串，在其它字符串中都有
     * @author Jef
     * @date 2021/4/18
     * @param s
     * @return int
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String fisrtStr = strs[0];
        if ("".equals(fisrtStr)) {
            return "";
        }
        String result = "";
        // 先得出最短字符串的长度
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].split("").length < minLength ) {
                minLength = strs[i].split("").length;
            }
        }

        for (int i = 1; i <= minLength; i++) {
            String subStr = fisrtStr.substring(0, i);
            boolean allExist = true;
            for (int k = 1; k < strs.length; k++) {
                if (strs[k].indexOf(subStr) != 0) {
                    allExist = false;
                }
            }
            if (allExist) {
                result = subStr;
            }
        }
        return result;
    }
}