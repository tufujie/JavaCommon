package com.jef.designpattern.creation.factory;

import com.jef.designpattern.BasicDesign;
import com.jef.designpattern.creation.factory.withDesign.factoryMethod.IScreen;
import com.jef.designpattern.creation.factory.withDesign.factoryMethod.ScreenFactory;
import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2021/12/6
 */
public class FactoryMethodTest {

    @Test
    public void testFactoryMethod() {
        // 想看三星显示器的制造工艺
        String text = BasicDesign.SAMSUNG_SCREEN;
        IScreen screen = ScreenFactory.createApi(text);
        screen.operation(2);
        // 突然又想看飞利浦的制造工艺
        text = BasicDesign.PHILIPS_SCREEN;
        screen = ScreenFactory.createApi(text);
        screen.operation(3);
    }

}