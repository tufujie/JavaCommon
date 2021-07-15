package com.jef.thread.concurrencyInPractice;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Vector;

/**
 * 更好的vector
 * @author Jef
 * @date 2020/6/22
 */
@ThreadSafe
public class BetterVector<E> extends Vector<E> {

    /**
     * 缺少元素才添加元素
     * @author Jef
     * @date 2020/6/22
     * @param x
     * @return boolean
     */
    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}