package com.jef.algorithm.leetcode;

/**
 * 左旋转字符串
 *
 * @author Jef
 * @date 2021/5/30
 */
public class ReverseLeftWords {

    public static void main(String[] args) {
        System.out.println(new ReverseLeftWords().reverseLeftWords("abcdefg", 2));
        System.out.println(new ReverseLeftWords().reverseLeftWords("lrloseumgh", 6));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param s
     * @return int
     */
    public String reverseLeftWords(String s, int n) {
        String a = s.substring(0, n), b = s.substring(n);
        return b + a;
    }

}