package com.jef.designpattern.structure.decorator.two;

/**
 * 左右边框装饰类
 *
 * @author Jef
 * @date 2023/6/30
 */
public class SideBorder extends Border {

    /**
     * 装饰的字符
     */
    private String borderChar;

    public SideBorder(Display display, String borderChar) {
        super(display);
        this.borderChar = borderChar;
    }

    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    @Override
    public int getRows() {
        return display.getRows();
    }

    @Override
    public String getRowText(int i) {
        return borderChar + display.getRowText(i) + borderChar;
    }
}