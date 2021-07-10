package com.jef.collection.setdemo;

import org.junit.Test;
import java.util.TreeSet;

/**
 * @author Jef
 * @date 2019/7/22
 */
public class TreeSetTest {

    /**
     * 测试TreeSet的排序
     * TreeSet从第一个元素到最后一个元素同树一样，树干越来越大，即升序排序
     */
    @Test
    public void testTreeSetSort() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(1);
        treeSet.add(3);
        treeSet.add(2);
        System.out.println(treeSet);
    }
}