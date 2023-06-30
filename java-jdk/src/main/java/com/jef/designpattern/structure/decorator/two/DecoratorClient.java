package com.jef.designpattern.structure.decorator.two;

/**
 * 装饰器模式-客户端
 *
 * @author Jef
 * @date 2023/6/30
 */
public class DecoratorClient {

    public static void main(String[] args) {
        Display d1 = new StringDisplay("Hello");
        // 给d1装饰
        Display d2 = new SideBorder(d1, "#");
        Display d3 = new FullBorder(d1);
        // 各自展示
        d1.show();
        d2.show();
        d3.show();
        // 多层装饰
        Display d4 = new SideBorder(new FullBorder(new FullBorder(d1)), "/");
        d4.show();
    }
}