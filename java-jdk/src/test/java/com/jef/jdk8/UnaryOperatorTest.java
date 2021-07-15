package com.jef.jdk8;

import org.junit.Test;

import java.util.function.UnaryOperator;

/**
 * 一元操作
 * @author Jef
 * @date 2021/2/20
 */
public class UnaryOperatorTest {
    @Test
    public void testApply() {
        UnaryOperator<Integer> dda = x -> x + 1;
        System.out.println(dda.apply(10));// 11
        UnaryOperator<String> ddb = x -> x + 1;
        System.out.println(ddb.apply("aa"));
        UnaryOperator<Boolean> unaryOperator = uglily -> !uglily;
        Boolean apply2 = unaryOperator.apply(true);
        System.out.println(apply2);
    }

}