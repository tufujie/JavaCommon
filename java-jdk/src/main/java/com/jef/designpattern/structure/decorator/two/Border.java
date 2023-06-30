package com.jef.designpattern.structure.decorator.two;

/**
 * @author Jef
 * @date 2023/6/30
 */
public abstract class Border extends Display {

    // 被装饰物
    protected Display display;

    // 在生成实例时通过传参指定被装饰物
    protected Border(Display display) {
        this.display = display;
    }
}