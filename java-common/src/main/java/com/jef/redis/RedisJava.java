package com.jef.redis;

import redis.clients.jedis.Jedis;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Jef
 */
public class RedisJava {
    public static void main(String[] args) {
        Jedis jedis = getAuthJedis();
        getString(jedis);
        getList(jedis);
        getSet(jedis);
    }

    /**
     * 获取认证的Jedis对象
     * @return
     */
    public static Jedis getAuthJedis() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 使用 CONFIG SET requirepass "root"，其中root可自定义，如果已经设置了，
        // 可以通过CONFIG GET requirepass获取
        // (error) NOAUTH Authentication required.如果出现这个错误，则说明没有验证，使用AUTH yourpassword进行验证
        jedis.auth("root");
        System.out.println("连接成功");
        // 查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        return jedis;
    }

    /**
     * Redis Java String(字符串) 实例
     * @param jedis
     */
    private static void getString(Jedis jedis) {

        // 设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));
    }

    /**
     * Redis Java List(列表) 实例
     * @param jedis
     */
    private static void getList(Jedis jedis) {
        // 存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }


    /**
     * Redis Java Keys 实例
     * @param jedis
     */
    private static void getSet(Jedis jedis) {
        // 获取Jedis中的所有key数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }
    }
}
