package com.jef.designpattern.structure.composite;

/**
 * @author Jef
 * @date 2023/6/30
 */
public class File extends Entry {
    private String name;

    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void printList(String prifix) {
        System.out.println(prifix + "/" + this);
    }
}