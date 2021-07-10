package com.jef.collection.setdemo;

import com.google.common.collect.Lists;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Set初始化，可以参考List初始化
 * @see com.jef.collection.listdemo.ListInitDemo
 * @author Jef
 * @date 2019/5/31
 */
public class SetInitDemo {

    public static void main(String[] args) {
        Set<String> setInit1 = new HashSet<>();
        setInit1.add("test");
        setInit1.add("test");
        setInit1.add("test2");
        System.out.println(setInit1);

        // 方式2
        Set<String> setInit2 = new HashSet<String>() {{
            add("test");
            add("test2");
            add("test");
            add("test3");
        }};
        System.out.println(setInit2);

        List<String> list = Lists.newArrayList();
        list.add("test");
        list.add("test");
        list.add("test2");
        Set<String> setInit3 = new HashSet<>(list);
        System.out.println(setInit3);
    }
}