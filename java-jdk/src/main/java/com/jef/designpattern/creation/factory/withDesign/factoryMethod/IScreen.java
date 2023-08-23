package com.jef.designpattern.creation.factory.withDesign.factoryMethod;

/**
 * 接口，输出文本接口
 * @author Jef
 * @
 */
public interface IScreen {
    /**
     * 显示器制造工艺
     *
     * @param n 个数，额外传参
     */
    void operation(int n);
}
