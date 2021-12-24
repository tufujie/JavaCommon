package com.jef.algorithm;

import org.junit.jupiter.api.Test;

/**
 * 左旋转字符串测试
 *
 * @author Jef
 * @date 2021/12/23
 */
public class ReverseLeftWordsTest {

    @Test
    public void testReverseLeftWords() {
        System.out.println(new ReverseLeftWords().reverseLeftWords("abcdefg", 2));
        System.out.println(new ReverseLeftWords().reverseLeftWords("lrloseumgh", 6));
    }
}