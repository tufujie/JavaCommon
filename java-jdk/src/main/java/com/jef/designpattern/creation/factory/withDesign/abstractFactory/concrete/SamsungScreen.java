package com.jef.designpattern.creation.factory.withDesign.abstractFactory.concrete;

import com.jef.designpattern.creation.factory.withDesign.abstractFactory.framework.Screen;

/**
 * 具体产品-三星显示器
 *
 * @author Jef
 * @date 2023/6/27
 */
public class SamsungScreen implements Screen {

    public SamsungScreen() {
        System.out.println("制造三星显示器");
    }

    @Override
    public void watch() {
        System.out.println("观看三星显示器");
    }
}