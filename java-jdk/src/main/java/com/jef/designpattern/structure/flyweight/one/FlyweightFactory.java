package com.jef.designpattern.structure.flyweight.one;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂
 * @author Jef
 * @date 2019/2/21
 */
public class FlyweightFactory {
    private static FlyweightFactory factory = new FlyweightFactory();

    private FlyweightFactory() {

    }

    public static FlyweightFactory getInstance() {
        return factory;
    }

    /**
     * 缓存多个Flyweight对象
     */
    private Map<String, FlyWeight> fsMap = new HashMap<String, FlyWeight>();

    /**
     * 获取 key 对应的享元对象
     * @author Jef
     * @date 2019/2/21
     * @param key 获取享元对象的key，只是示意
     * @return 对应的享元对象
     */
    public FlyWeight getFlyweight(String key) {
        // 这个方法中基本的实现步骤如下：
        // 1：先从缓存中查找，时候存在key对应的Flyweight对象
        FlyWeight f = fsMap.get(key);
        // 2：如果存在，就返回对应的Flyweight对象
        if (f == null) {
            // 3：如果不存在
            // 3.1：创建一个新的Flyweight对象
            f = new AuthorizationFlyweight(key);
            // 3.2：把这个新的Flyweight对象添加到缓存中
            fsMap.put(key, f);
            // 3.3：然后返回这个新的Flyweight对象
        }
        return f;
    }
}