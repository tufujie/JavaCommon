package com.jef.designpattern.creation.factoryMethod.withDesign.factoryMethod.concrete;

import com.jef.designpattern.creation.factoryMethod.withDesign.factoryMethod.framework.Screen;
import com.jef.designpattern.creation.factoryMethod.withDesign.factoryMethod.framework.ScreenFactory;

/**
 * 具体产品工厂-三星显示器工厂
 *
 * @author Jef
 * @date 2023/6/27
 */
public class SamsungScreenFactory extends ScreenFactory {

    @Override
    protected Screen createScreen() {
        return new SamsungScreen();
    }

    @Override
    protected void testScreen(Screen screen) {
        System.out.println("测试三星显示器");
    }
}