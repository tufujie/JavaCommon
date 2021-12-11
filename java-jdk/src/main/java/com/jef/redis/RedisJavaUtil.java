package com.jef.redis;

import com.jef.util.PrintUtil;

import redis.clients.jedis.Jedis;

/**
 * @author Jef
 */
public class RedisJavaUtil {

    /**
     * 获取认证的Jedis对象
     *
     * @return
     */
    public static Jedis getAuthJedis() {
        // 连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        // 使用 CONFIG SET requirepass "root"，其中root可自定义，如果已经设置了，
        // 可以通过CONFIG GET requirepass获取
        // (error) NOAUTH Authentication required.如果出现这个错误，则说明没有验证，使用AUTH yourpassword进行验证
        jedis.auth("root");
        System.out.println("Redis连接成功");
        // 查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        PrintUtil.printSplitLine();
        return jedis;
    }
}
