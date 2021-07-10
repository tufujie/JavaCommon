package com.jef.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HashSetTest {
    public static void main(String[] args) {
        Set<String> setOne = new HashSet<>();
        Collections.addAll(setOne, "A,B,C".split(","));
        System.out.println("集合中是否包含某个元素");
        System.out.println(setOne.contains("A"));
        Set<String> setTwo = new HashSet<>(Arrays.asList("A,B".split(",")));
        System.out.println("A集合是否包含B集合");
        System.out.println(setOne.containsAll(setTwo));
        System.out.println("从A集合移除B集合中含有的元素");
        setTwo.add("D");
        setOne.removeAll(setTwo);
        System.out.println(setOne);
    }
}
