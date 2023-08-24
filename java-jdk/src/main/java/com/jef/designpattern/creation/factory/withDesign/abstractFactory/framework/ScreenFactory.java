package com.jef.designpattern.creation.factory.withDesign.abstractFactory.framework;

/**
 * 工厂-生产显示器
 * 使用抽象类的原因：有相同的方法实现和不同的方法实现
 * @author Jef
 * @date 2023/6/27
 */
public abstract class ScreenFactory {

    /**
     * 制造-显示器
     * 模拟的是一些公共、相同、规范的流程
     */
    public final Screen create() {
        Screen screen = createScreen();
        testScreen(screen);
        return screen;
    }

    /**
     * 创建-显示器
     * 制作过程不统一
     * 模拟的是不相同的流程，即方法实现
     */
    protected abstract Screen createScreen();

    /**
     * 测试-显示器，泛指其它流程
     * 测试流程不统一
     * 模拟的是不相同的流程，即方法实现
     */
    protected abstract void testScreen(Screen screen);
}