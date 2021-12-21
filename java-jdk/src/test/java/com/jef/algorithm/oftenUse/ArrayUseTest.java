package com.jef.algorithm.oftenUse;

import org.junit.jupiter.api.Test;

/**
 * 数组下标测试
 *
 * @author Jef
 * @date 2021/12/21
 */
public class ArrayUseTest {

    @Test
    public void testCalcChar() {
        char[] strArray = new char[]{'a', 'b', 'c', 'c'};
        ArrayIndex.calcChar(strArray);
    }

}