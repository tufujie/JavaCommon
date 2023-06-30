package com.jef.designpattern.structure.decorator.two;

/**
 * @author Jef
 * @date 2023/6/30
 */
public abstract class Display {

    /**
     * 每一行的字符数
     */
    public abstract int getColumns();

    /**
     * 行数
     */
    public abstract int getRows();

    /**
     * 获取第i行的字符串
     */
    public abstract String getRowText(int i);

    /**
     * 显示字符串
     */
    public void show() {
        for (int i = 0; i < getRows(); i++) {
            System.out.println(getRowText(i));
        }
    }
}