package com.jef.designpattern.structure.decorator.two;

/**
 * 全边框装饰类
 *
 * @author Jef
 * @date 2023/6/30
 */
public class FullBorder extends Border {

    public FullBorder(Display display) {
        super(display);
    }

    @Override
    public int getColumns() {
        return 1 + display.getColumns() + 1;
    }

    @Override
    public int getRows() {
        return 1 + display.getRows() + 1;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {
            // 上边框
            return "+" + makeLine("-", display.getColumns()) + "+";
        } else if (row == display.getRows() + 1) {
            // 下边框
            return "+" + makeLine("-", display.getColumns()) + "+";
        } else {
            return "|" + display.getRowText(row - 1) + "|";
        }
    }

    private String makeLine(String borderChar, int columns) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            sb.append(borderChar);
        }
        return sb.toString();
    }
}