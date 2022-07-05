package com.jef.algorithm.sort;

import java.util.Comparator;

/**
 * 排序器接口(策略模式：将算法封装到具有共同接口的独立的类中使得它们可
 * 以相互替换)*@author Jef
 */
public interface Sorter {
    /**
     * 排序
     *
     * @param list待排序的数组
     */
    <T extends Comparable<T>> void sort(T[] list);

    /**
     * 排序
     *
     * @param list待排序的数组
     * @param comp比较两个对象的比较器
     */
    <T> void sort(T[] list, Comparator<T> comp);
}