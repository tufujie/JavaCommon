package com.jef.jdk8;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 判断真假
 * @author Jef
 * @date 2021/2/20
 */
public class PredicateTest {
    @Test
    public void testTest() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        Assert.assertTrue(predicate.test("foo"));
        Assert.assertFalse(predicate.negate().test("foo"));
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
    }
}
