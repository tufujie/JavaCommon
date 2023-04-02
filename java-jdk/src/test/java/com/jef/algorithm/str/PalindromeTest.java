package com.jef.algorithm.str;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2023/4/1
 */
public class PalindromeTest {

    @Test
    public void testLongestPalindrome() {
        System.out.println(Palindrome.longestPalindrome("abccccdd"));
    }

    @Test
    public void testIsPalindrome() {
        System.out.println(Palindrome.isPalindrome("A man, a plan, a canal: Panama"));
    }

    @Test
    public void testLongestPalindromeSub() {
        System.out.println(Palindrome.longestPalindromeSub("babad"));
    }

    @Test
    public void testLongestPalindromeSubseq() {
        System.out.println(Palindrome.longestPalindromeSubseq("bbbab"));
    }

    @Test
    public void testBracketsPichDepth() {
        System.out.println(Palindrome.bracketsPichDepth("(())"));
    }

}