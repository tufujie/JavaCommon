package com.jef.algorithm.str;

import java.util.Arrays;

/**
 * 最⻓公共前缀
 * 编写⼀个函数来查找字符串数组中的最⻓公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输⼊: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输⼊: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输⼊不存在公共前缀。
 * @author Jef
 * @date 2023/4/1
 */
public class LongestCommonPrefix {

    public static String getLongestCommonPrefix(String[] strs) {
        // 如果检查值不合法及就返回空串
        if (!checkStrs(strs)) {
            return "";
        }
        // 数组⻓度
        int len = strs.length;
        // ⽤于保存结果
        StringBuilder res = new StringBuilder();
        // 给字符串数组的元素按照升序排序(包含数字的话，数字会排在前⾯)
        Arrays.sort(strs);
        int m = strs[0].length();
        int n = strs[len - 1].length();
        int num = Math.min(m, n);
        for (int i = 0; i < num; i++) {
            if (strs[0].charAt(i) == strs[len - 1].charAt(i)) {
                res.append(strs[0].charAt(i));
            } else
                break;
        }
        return res.toString();
    }

    private static boolean checkStrs(String[] strs) {
        boolean flag = false;
        if (strs != null) {
            // 遍历strs检查元素值
            for (int i = 0; i < strs.length; i++) {
                if (strs[i] != null && strs[i].length() != 0) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

}