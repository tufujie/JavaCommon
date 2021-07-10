package com.jef.jdk8;

import org.junit.Test;

import java.util.function.BinaryOperator;

/**
 * 二元操作
 * @author Jef
 * @date 2021/2/20
 */
public class BinaryOperatorTest {
    @Test
    public void testApply() {
        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer integer = operator.apply(2, 3);
        System.out.println(integer);
    }
}