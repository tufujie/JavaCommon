package com.jef.collection.listdemo;

import java.util.AbstractList;
import java.util.List;

/**
 * 任意长的集合初始化
 *
 * @author Jef
 * @date 2019/7/10
 */
public class CountingIntegerList extends AbstractList<Integer> {
    private int size;

    public CountingIntegerList(int size) {
        this.size = size < 0 ? 0 : size;
    }

    @Override
    public Integer get(int index) {
        return index;
    }

    @Override
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        List<Integer> integerList = new CountingIntegerList(30);
        System.out.println(integerList);
    }
}