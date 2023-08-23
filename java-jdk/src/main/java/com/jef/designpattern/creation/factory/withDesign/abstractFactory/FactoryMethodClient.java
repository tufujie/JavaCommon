package com.jef.designpattern.creation.factory.withDesign.abstractFactory;

import com.jef.designpattern.creation.factory.withDesign.abstractFactory.concrete.PhilipsScreenFactory;
import com.jef.designpattern.creation.factory.withDesign.abstractFactory.concrete.SamsungScreenFactory;
import com.jef.designpattern.creation.factory.withDesign.abstractFactory.framework.Screen;
import com.jef.designpattern.creation.factory.withDesign.abstractFactory.framework.ScreenFactory;

/**
 * 工厂方法客户端
 *
 * @author Jef
 * @date 2023/6/27
 */
public class FactoryMethodClient {

    public static void main(String[] args) {
        // 飞利浦显示器
        ScreenFactory philipsScreenFactory = new PhilipsScreenFactory();
        Screen philipsScreen = philipsScreenFactory.create();
        philipsScreen.watch();

        // 三星显示器
        ScreenFactory samsungScreenFactory = new SamsungScreenFactory();
        Screen samsungScreen = samsungScreenFactory.create();
        samsungScreen.watch();
    }
}