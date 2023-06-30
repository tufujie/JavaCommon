package com.jef.designpattern.structure.composite;

/**
 * Entry
 *
 * @author Jef
 * @date 2023/6/30
 */
public abstract class Entry {

    public abstract String getName();

    public abstract int getSize();

    public void printList() {
        printList("");
    }

    /**
     * 打印带前缀的方法
     *
     * @param prifix 前缀
     */
    public abstract void printList(String prifix);

    @Override
    public String toString() {
        return getName() + "(" + getSize() + ")";
    }

}