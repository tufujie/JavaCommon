package com.jef.designpattern.creation.factory.withoutDesign;

import com.jef.designpattern.BasicDesign;

/**
 * 客户端功能调用
 * @author Jef
 * @create 20180707
 */
public class Client {
    public static void main(String[] args) {
        // 功能实现了，但是，在客户端既知道了接口是Api，又知道了接口的实现是Impl，没有遵循接口的“封装隔离”，
        Api api = new Impl();
        api.test(BasicDesign.SAMSUNG_SCREEN);
    }
}
