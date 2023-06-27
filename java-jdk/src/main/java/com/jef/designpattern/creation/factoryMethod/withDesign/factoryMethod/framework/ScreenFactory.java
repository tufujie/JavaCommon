package com.jef.designpattern.creation.factoryMethod.withDesign.factoryMethod.framework;

/**
 * 工厂-生产显示器
 *
 * @author Jef
 * @date 2023/6/27
 */
public abstract class ScreenFactory {

    /**
     * 制造-显示器
     */
    public final Screen create() {
        Screen screen = createScreen();
        testScreen(screen);
        return screen;
    }

    /**
     * 创建-显示器
     */
    protected abstract Screen createScreen();

    /**
     * 测试-显示器，泛指其它流程
     */
    protected abstract void testScreen(Screen screen);
}