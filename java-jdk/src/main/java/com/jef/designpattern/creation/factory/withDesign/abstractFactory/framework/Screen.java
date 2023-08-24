package com.jef.designpattern.creation.factory.withDesign.abstractFactory.framework;

/**
 * 产品-显示器
 * 使用接口的原因：让实现类各自实现，即方法实现不同
 * @author Jef
 * @date 2023/6/27
 */
public interface Screen {

    /**
     * 看-显示器
     * 模拟的是不同的功能
     */
    void watch();
}