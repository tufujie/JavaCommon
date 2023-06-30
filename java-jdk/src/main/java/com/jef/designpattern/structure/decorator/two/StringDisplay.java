package com.jef.designpattern.structure.decorator.two;

/**
 * 显示一行字符串的类
 *
 * @author Jef
 * @date 2023/6/30
 */
public class StringDisplay extends Display {

    private String string;

    public StringDisplay(String string) {
        this.string = string;
    }

    @Override
    public int getColumns() {
        return string.getBytes().length;
    }

    @Override
    public int getRows() {
        return 1;
    }

    @Override
    public String getRowText(int i) {
        return i == 0 ? string : null;
    }
}