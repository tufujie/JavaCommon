package com.jef.designpattern.simpleFactory.withDesign;

import com.jef.designpattern.BasicDesign;

/**
 * 简单工厂，根据条件创建产品
 * @author Jef
 * @create 20180707
 */
public class Factory {

    /**
     * 根据条件创建接口，告诉我需要哪个牌子的显示器，我给你生产出来
     */
    public static IScreen createApi(String condition) {
        IScreen screen = null;
        if (condition.equals(BasicDesign.SAMSUNG_SCREEN)) {
            screen = new SamsungScreenImpl();
        } else if (condition.equals(BasicDesign.PHILIPS_SCREEN)) {
            screen = new PhilipsScreenImpl();
        }
        return screen;
    }
}
