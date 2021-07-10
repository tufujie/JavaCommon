package com.jef.jdk8;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 方法引用
 * @author Jef
 * @date 2019/3/14
 */
public class MethodUseDemo {

    public static void main(String[] args) {
        // 引用静态方法	ClassName::staticMethodName
        Predicate<String> a1 = s -> Objects.nonNull(s);
        Predicate<String> a2 = Objects::nonNull;
        // 引用外部对象的实例方法	ObjectName::instanceMethodName
        String test = "test";
        Predicate<String> b1 = s -> test.contains(s);
        Predicate<String> b2 = test::contains;
        // 引用第一个参数的类型的实例方法	ClassName::instanceMethodName
        Function<String, Integer> c1 = s -> s.length();
        Function<String, Integer> c2 = String::length;
        BiFunction<String, Integer, Character> d1 = (s, i) -> s.charAt(i);
        BiFunction<String, Integer, Character> d2 = String::charAt;
        // 引用构造方法	ClassName::new
        Function<Integer, String[]> e1 = i -> new String[i];
        Function<Integer, String[]> e2 = String[]::new;

    }
}