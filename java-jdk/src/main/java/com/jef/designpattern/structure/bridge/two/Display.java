package com.jef.designpattern.structure.bridge.two;

/**
 * @author Jef
 * @date 2023/6/29
 */
public class Display {

    private DisplayImpl displayImpl;

    public Display(DisplayImpl displayImpl) {
        this.displayImpl = displayImpl;
    }

    public void open() {
        displayImpl.open();
    }

    public void print() {
        displayImpl.print();
    }

    public void close() {
        displayImpl.close();
    }

    void display() {
        open();
        print();
        close();
    }

}