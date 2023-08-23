package com.jef.designpattern.creation.factory.withDesign.factoryMethod;

import com.jef.designpattern.BasicDesign;

/**
 * 工厂方法模式、工厂模式，根据条件创建产品
 *
 * @author Jef
 * @create 20180707
 */
public class ScreenFactory {

    /**
     * 根据条件创建接口，告诉我需要哪个牌子的显示器，我给你生产出来
     */
    public static IScreen createApi(String condition) {
        IScreen screen = null;
        if (BasicDesign.SAMSUNG_SCREEN.equals(condition)) {
            screen = new SamsungScreenImpl();
        } else if (BasicDesign.PHILIPS_SCREEN.equals(condition)) {
            screen = new PhilipsScreenImpl();
        }
        return screen;
    }
}
