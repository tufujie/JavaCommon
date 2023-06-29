package com.jef.designpattern.structure.bridge.two;

/**
 * @author Jef
 * @date 2023/6/29
 */
public class CountDisplay extends Display {

    public CountDisplay(DisplayImpl displayImpl) {
        super(displayImpl);
    }

    /**
     * 打印多次，子类自己的方法
     *
     * @param count
     */
    public void multiDisplay(int count) {
        open();
        for (int i = 0; i < count; i++) {
            print();
        }
        close();
    }

}