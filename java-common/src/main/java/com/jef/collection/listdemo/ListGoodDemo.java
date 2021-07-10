package com.jef.collection.listdemo;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jef on 2017-06-30.
 */
public class ListGoodDemo {
    public static void main(String[] args) {
        System.out.println(integerList1);
        System.out.println(integerList2);
    }

    public static final List<Integer> integerList1 = Lists.newArrayList(new Integer[]{1, 2, 3});

    public static final List<Integer> integerList2 = new ArrayList() {
        {
            add(1);
            add(2);
            add(3);
        }
    };
}
