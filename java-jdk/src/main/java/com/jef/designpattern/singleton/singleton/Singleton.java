package com.jef.designpattern.singleton.singleton;

/**
 * 懒汉式单例模式-（个人记法：懒得为类实例赋值）
 */
public class Singleton {
    private static Singleton uniqueSingleton  = null;

    /**
     * 定义一个方法来为客户端提供类实例
     * @return
     */
    public static synchronized Singleton getInstance() {
        if(uniqueSingleton == null)
            uniqueSingleton = new Singleton();
        return uniqueSingleton;
    }
}
