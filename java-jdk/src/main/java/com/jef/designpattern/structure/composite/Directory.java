package com.jef.designpattern.structure.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Jef
 * @date 2023/6/30
 */
public class Directory extends Entry {
    private String name;

    private int size;

    private List<Entry> entryList = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        size = 0;
        Iterator iterator = entryList.iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            size += entry.getSize();
        }
        return size;
    }

    @Override
    public void printList(String prifix) {
        System.out.println(prifix + "/" + this);
        Iterator iterator = entryList.iterator();
        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            entry.printList(prifix + "/" + name);
        }
    }

    public void add(Entry entry) {
        entryList.add(entry);
    }
}