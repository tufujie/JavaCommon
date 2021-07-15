package com.jef.collection.listdemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * 集合删除
 * @author Jef
 * @date 2020/7/22
 */
public class ArrayListDelete {

    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.equals("a") || p.equals("b");
        // 可以正常删除结果正确
        deleteByIterator(getList(), predicate);
        // 可以正常删除结果正确
        deleteByReverseOrder(getList(), predicate);
        // 可以删除 结果不正确
        deleteByOrder(getList(), predicate);
        // 不能删除 报错java.util.ConcurrentModificationException
        deleteByArrayList(getList(), predicate);
        // 不能删除 报错java.util.ConcurrentModificationException
        deleteByForeach(getList(), predicate);
        // 不能删除 报错 java.util.ConcurrentModificationException
        deleteByEnhancedForLoop(getList(), predicate);
        // 正常删除数据
        deleteAll(getList(), predicate);

    }

    public static  List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        return list;
    }



    /**
     * 普通for循环倒序删除
     * 可以正常删除  结果正确
     * @param list
     * @param predicate
     */
    public static void deleteByReverseOrder(List<String> list, Predicate<String> predicate) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (predicate.test(list.get(i))) {
                list.remove(list.get(i));
            }
        }
    }

    /**
     * 普通for循环正序删除
     * 可以删除  结果不正确
     * @param list
     * @param predicate
     */

    public static void deleteByOrder(List<String> list, Predicate<String> predicate) {
        for (int i = 0; i < list.size(); i++) {
            if (predicate.test(list.get(i))) {
                list.remove(list.get(i));
            }
        }
    }


    /**
     * 迭代器循环，使用ArrayList的remove()方法删除
     * 可以删除  结果不正确
     * @param list
     * @param predicate
     */
    public static void deleteByArrayList(List<String> list, Predicate<String> predicate) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                list.remove(iterator.next());
            }
        }
    }

    /**
     * java8 forEach方法删除
     * 不能删除 报错 java.util.ConcurrentModificationException
     * @param list
     * @param predicate
     */
    public static void deleteByForeach(List<String> list, Predicate<String> predicate) {
        list.forEach(p -> {
            if (predicate.test(p)) {
                list.remove(p);
            }
        });
    }
    /**
     * 增强版for循环删除
     * 不能删除 报错 java.util.ConcurrentModificationException
     * @param list
     * @param predicate
     */
    public static void deleteByEnhancedForLoop(List<String> list, Predicate<String> predicate) {
        for (String string : list) {
            if (predicate.test(string)) {
                list.remove(string);
            }
        }
    }

    /**
     * 调用批量删除方法
     * 推荐用法
     * @param list
     * @param predicate
     */
    public static void deleteAll(List<String> list, Predicate<String> predicate) {
        List<String> removeList = new ArrayList<>();
        for (String string : list) {
            if (predicate.test(string)) {
                removeList.add(string);
            }
        }
        list.removeAll(removeList);
    }

    /**
     * 迭代器循环，使用迭代器的remove()方法删除
     * 可以正常删除结果正确
     * @param list
     * @param predicate
     */
    public static void deleteByIterator(List<String> list, Predicate<String> predicate) {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                iterator.remove();
            }
        }
    }
}