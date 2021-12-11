package com.jef.designpattern.creation.singleton;

/**
 * 饿汉式单例模式-（个人记法：太饿了，先吃饱了再说）
 */
public class SingletonTwo {
    private static SingletonTwo uniqueSingletonTwo = new SingletonTwo();

    public static SingletonTwo getInstance() {
        return uniqueSingletonTwo;
    }
}
