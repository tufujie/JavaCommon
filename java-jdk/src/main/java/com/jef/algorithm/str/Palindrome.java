package com.jef.algorithm.str;

import java.util.HashSet;

/**
 * 回文串
 *
 * @author Jef
 * @date 2023/4/1
 */
public class Palindrome {

    /**
     * 最⻓回⽂串
     * 给定⼀个包含⼤写字⺟和⼩写字⺟的字符串，找到通过这些字⺟构造成的最⻓的回⽂串。在构造过程中，请注意区分⼤⼩写。⽐如 "Aa" 不能当做⼀个回⽂字符串。
     * 注意:假设字符串的⻓度不会超过 1010。
     * 输⼊:
     * "abccccdd"
     * 输出:
     * 7
     * 解释:
     * 我们可以构造的最⻓的回⽂串是"dccaccd", 它的⻓度是 7。
     */
    public static int longestPalindrome(String s) {
        if (s.length() == 0)
            return 0;
        // ⽤于存放字符
        HashSet<Character> hashSet = new HashSet<Character>();
        char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if (!hashSet.contains(chars[i])) {// 如果hashSet没有该字符就保存进去
                hashSet.add(chars[i]);
            } else {// 如果有,就让count++（说明找到了⼀个成对的字符），然后把该字符移除
                hashSet.remove(chars[i]);
                count++;
            }
        }
        return hashSet.isEmpty() ? count * 2 : count * 2 + 1;
    }

    /**
     * 验证回⽂串
     * 给定⼀个字符串，验证它是否是回⽂串，只考虑字⺟和数字字符，可以忽略字⺟的⼤⼩写。 说明：本题中，我们将空字符串定义为有效的回⽂串。
     * 示例 1:
     * 输⼊: "A man, a plan, a canal: Panama"
     * 输出: true
     * 示例 2:
     * 输⼊: "race a car"
     * 输出: false
     */
    public static boolean isPalindrome(String s) {
        if (s.length() == 0) {
            return true;
        }
        int l = 0, r = s.length() - 1;
        while (l < r) {
            // 从头和尾开始向中间遍历
            if (!Character.isLetterOrDigit(s.charAt(l))) {// 字符不是字⺟和数字的情况
                l++;
            } else if (!Character.isLetterOrDigit(s.charAt(r))) {// 字符不是字⺟和数字的情况
                r--;
            } else {
                // 判断⼆者是否相等
                if (Character.toLowerCase(s.charAt(l)) !=
                        Character.toLowerCase(s.charAt(r))) {
                    return false;
                }
                l++;
                r--;
            }
        }
        return true;
    }

    private static int index;
    private static int len;

    /**
     * 最⻓回⽂⼦串
     * 最⻓回⽂⼦串 给定⼀个字符串 s，找到 s 中最⻓的回⽂⼦串。你可以假设 s 的最⼤⻓度为1000。
     * 示例 1：
     * 输⼊: "babad"
     * 输出: "bab"
     * 注意: "aba"也是⼀个有效答案。
     * 示例 2：
     * 输⼊: "cbbd"
     * 输出: "bb"
     */
    public static String longestPalindromeSub(String s) {
        if (s.length() < 2) {
            return s;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            palindromeHelper(s, i, i);
            palindromeHelper(s, i, i + 1);
        }
        return s.substring(index, index + len);
    }

    public static void palindromeHelper(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        if (len < r - l - 1) {
            index = l + 1;
            len = r - l - 1;
        }
    }

    /**
     * 最⻓回⽂⼦序列
     * 给定⼀个字符串s，找到其中最⻓的回⽂⼦序列。可以假设s的最⼤⻓度为1000。最⻓回⽂⼦序列和上⼀题最⻓回⽂⼦串的区别是，⼦串是字符串中连续的⼀个序列，⽽⼦序
     * 列是字符串中保持相对位置的字符序列，例如，"bbbb"可以是字符串"bbbab"的⼦序列但不是⼦串。
     * 示例 1:
     * 输⼊:
     * "bbbab"
     * 输出:
     * 4
     * ⼀个可能的最⻓回⽂⼦序列为 "bbbb"。
     * 示例 2:
     * 输⼊:
     * "cbbd"
     * 输出:
     * 2
     * ⼀个可能的最⻓回⽂⼦序列为 "bb"。
     */
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    /**
     * 括号匹配深度
     * 输⼊描述:
     * 输⼊包括⼀个合法的括号序列s,s⻓度length(2 ≤ length ≤ 50),序列中只包含'('和')'。
     * 输出描述:
     * 输出⼀个正整数,即这个序列的深度。
     * 示例：
     * 输⼊:
     * (())
     * 输出:
     * 2
     */
    public static int bracketsPichDepth(String s) {
        int cnt = 0, max = 0, i;
        for (i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }

}
