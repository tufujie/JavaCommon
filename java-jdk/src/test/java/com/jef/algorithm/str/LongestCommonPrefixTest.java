package com.jef.algorithm.str;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2023/4/1
 */
public class LongestCommonPrefixTest {

    @Test
    public void testGetMaxCommonPrefix() {
        String[] strs = {"customer", "car", "cat"};
        // String[] strs = {"customer", "car", null};//空串
        // String[] strs = {};//空串
        // String[] strs = null;//空串
        // c
        System.out.println(LongestCommonPrefix.getLongestCommonPrefix(strs));
        String[] strs2 = {"flower", "flow", "flow"};
        System.out.println(LongestCommonPrefix.getLongestCommonPrefix(strs2));
    }

}