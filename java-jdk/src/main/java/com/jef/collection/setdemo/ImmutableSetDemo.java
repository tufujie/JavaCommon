package com.jef.collection.setdemo;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.jef.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * ImmutableDemo
 * @author Jef
 * @date 2019/3/14
 */
public class ImmutableSetDemo {
    public static void main(String[] args) {
        String[] array = {"test1", "test2", "test3", "test1"};
        // 方式都实现了去重效果
        // 数组转Set方式1
        List<String> list1 = StringUtils.getListFromArray(array);
        Set<String> set1 = Sets.newHashSet(list1);
        System.out.println(set1);
        // 数组转Set方式2
        Set<String> set2 = ImmutableSet.copyOf(array);
        System.out.println(set2);
    }

}