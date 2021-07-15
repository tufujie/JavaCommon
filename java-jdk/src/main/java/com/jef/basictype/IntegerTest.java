package com.jef.basictype;

import org.junit.Test;

/**
 * Integer测试
 *
 * @author Jef
 * @date 2019/7/22
 */
public class IntegerTest {

    /**
     * Integer转String
     */
    @Test
    public void testIntegerToString() {
        Integer num = 1;
        // 方式1
        System.out.println(num.toString());
        // 方式2
        System.out.println(String.valueOf(num));
        // 方式3
        System.out.println(Integer.toString(num));
    }
}