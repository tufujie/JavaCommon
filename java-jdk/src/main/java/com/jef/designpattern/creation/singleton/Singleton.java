package com.jef.designpattern.creation.singleton;

/**
 * 懒汉式单例模式-线程安全-（个人记法：懒得为类实例赋值）
 *
 * @author Jef
 */
public class Singleton {
    private static Singleton uniqueInstance = null;

    /**
     * 必须是私有的，防止实例化
     */
    private Singleton() {
        System.out.println("实例化单例对象");
    }

    /**
     * 定义一个方法来为客户端提供类实例
     *
     * @return
     */
    public static synchronized Singleton getInstance() {
        if (uniqueInstance == null) {
            System.out.println("不存在实例，创建实例");
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
