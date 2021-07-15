package com.jef.collection.setdemo;

import com.google.common.collect.Sets;
import org.junit.Test;
import java.util.LinkedHashSet;

/**
 * @author Jef
 * @date 2019/7/22
 */
public class LinkedHashSetTest {
    /**
     * 测试LinkedHashSet的排序
     * LinkedHashSet从第一个元素到最后一个元素按照添加的顺序排序
     */
    @Test
    public void testLinkedHashSetSort() {
        LinkedHashSet<String> linkedHashSet = Sets.newLinkedHashSet();
        linkedHashSet.add("test2");
        linkedHashSet.add("test1");
        linkedHashSet.add("test3");
        System.out.println(linkedHashSet);
    }
}