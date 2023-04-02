package com.jef.algorithm.str;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2023/4/1
 */
public class StringFunctionTest {

    @Test
    public void testReplaceSpace() {
        System.out.println(StringFunction.replaceSpace("We Are Happy"));
    }

    @Test
    public void testReplaceSpace2() {
        System.out.println(StringFunction.replaceSpace2("We Are Happy"));
    }

    @Test
    public void testStrToInt() {
        String s = "-12312312";
        System.out.println("使⽤库函数转换：" + Integer.valueOf(s));
        int res = StringFunction.strToInt(s);
        System.out.println("使⽤⾃⼰写的⽅法转换：" + res);
    }

    @Test
    public void testGetMaxCommonPrefix() {
        String[] strs = {"customer", "car", "cat"};
        // String[] strs = {"customer", "car", null};//空串
        // String[] strs = {};//空串
        // String[] strs = null;//空串
        // c
        System.out.println(StringFunction.getLongestCommonPrefix(strs));
        String[] strs2 = {"flower", "flow", "flow"};
        System.out.println(StringFunction.getLongestCommonPrefix(strs2));
    }

}