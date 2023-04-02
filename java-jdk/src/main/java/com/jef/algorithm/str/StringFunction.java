package com.jef.algorithm.str;

import java.util.Arrays;

/**
 * 替换空格
 * 两种⽅法：①常规⽅法；②利⽤ API 解决。
 * 请实现⼀个函数，将⼀个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy。则经过替换之后的字符串为We%20Are%20Happy。
 *
 * @author Jef
 * @date 2023/4/1
 */
public class StringFunction {

    /**
     * 第⼀种⽅法：常规⽅法。利⽤String.charAt(i)以及String.valueOf(char).equals(" "
     * )遍历字符串并判断元素是否为空格。是则替换为"%20",否则不替换
     */
    public static String replaceSpace(String str) {
        int length = str.length();
        // System.out.println("length=" + length);
        String result = "";
        for (int i = 0; i < length; i++) {
            char b = str.charAt(i);
            if (String.valueOf(b).equals(" ")) {
                result += "%20";
            } else {
                result += b;
            }
        }
        return result;
    }

    /**
     * 第⼆种⽅法：利⽤API替换掉所⽤空格，⼀⾏代码解决问题
     */
    public static String replaceSpace2(String str) {
        return str.replaceAll("\\s", "%20");
//        return str.replaceAll(" ", "%20");
    }

    /**
     * 把字符串转换成整数
     * 将⼀个字符串转换成⼀个整数(实现Integer.valueOf(string)的功能，但是string不
     * 符合数字要求时返回0)，要求不能使⽤字符串转换整数的库函数。 数值为0或者字符串不是
     * ⼀个合法的数值则返回0。
     */
    public static int strToInt(String str) {
        if (str.length() == 0)
            return 0;
        char[] chars = str.toCharArray();
        // 判断是否存在符号位
        int flag = 0;
        if (chars[0] == '+') {
            flag = 1;
        } else if (chars[0] == '-') {
            flag = 2;
        }
        int start = flag > 0 ? 1 : 0;
        int res = 0;// 保存结果
        for (int i = start; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {// 调⽤Character.isDigit(char)⽅法判断是否是数字，是返回true，否则false
                int temp = chars[i] - '0';
                res = res * 10 + temp;
            } else {
                return 0;
            }
        }
        return flag != 2 ? res : -res;
    }

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
     */
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