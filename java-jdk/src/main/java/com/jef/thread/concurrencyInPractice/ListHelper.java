package com.jef.thread.concurrencyInPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jef
 * @date 2020/6/22
 */
public class ListHelper<E> {

    private List<E> list = Collections.synchronizedList(new ArrayList<E>());

    /**
     * 缺少元素才添加元素
     * @author Jef
     * @date 2020/6/22
     * @param x
     * @return boolean
     */
    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}