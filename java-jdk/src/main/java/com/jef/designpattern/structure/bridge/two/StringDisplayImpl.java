package com.jef.designpattern.structure.bridge.two;

/**
 * @author Jef
 * @date 2023/6/29
 */
public class StringDisplayImpl extends DisplayImpl {

    private String name;

    public StringDisplayImpl(String name) {
        this.name = name;
    }

    @Override
    public void open() {
        System.out.println(name + " String open");
    }

    @Override
    public void print() {
        System.out.println(name + " String print");
    }

    @Override
    public void close() {
        System.out.println(name + " String close");
    }
}