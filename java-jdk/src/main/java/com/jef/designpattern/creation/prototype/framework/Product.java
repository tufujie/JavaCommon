package com.jef.designpattern.creation.prototype.framework;

/**
 * 产品
 *
 * @author Jef
 * @date 2023/6/29
 */
public interface Product extends Cloneable {

    /**
     * 拷贝对象
     */
    Product createClone();

    /**
     * 使用产品
     */
    void use(String productName);
}