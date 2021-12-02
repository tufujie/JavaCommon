package com.jef.cache;

import com.jef.constant.BasicConstant;
import com.jef.redis.RedisServiceFactory;
import com.jef.redis.cache.ObjectCache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mock.env.MockEnvironment;

import java.lang.reflect.Field;

/**
 * @author Jef
 * @date 2021/12/2
 */
public class ObjectCacheTest {

    public void initRedis() throws NoSuchFieldException, IllegalAccessException {
        JedisConnectionFactory conn = new JedisConnectionFactory();
        conn.setDatabase(0);
        conn.setHostName("127.0.0.1");
        conn.setPort(6379);
        conn.setUsePool(true);
        conn.afterPropertiesSet();

        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(conn);
        redisTemplate.afterPropertiesSet();

        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(conn);
        stringRedisTemplate.afterPropertiesSet();

        Environment env = new MockEnvironment();

        RedisServiceFactory factory = RedisServiceFactory.getSingleton();
        Field f1 = RedisServiceFactory.class.getDeclaredField("redisTemplate");
        f1.setAccessible(true);
        f1.set(factory, redisTemplate);


        Field f2 = RedisServiceFactory.class.getDeclaredField("stringRedisTemplate");
        f2.setAccessible(true);
        f2.set(factory, stringRedisTemplate);

    }

    @BeforeEach
    public void init() throws NoSuchFieldException, IllegalAccessException {
        initRedis();
    }

    @Test
    public void testSetCache() {
        ObjectCache.setCache(BasicConstant.STR_ONE, BasicConstant.USER_NAME, 1000);
    }

    @Test
    public void testCheckCacheExistAndNotExpire() throws InterruptedException {
        boolean cacheExistAndNotExpire = ObjectCache.checkCacheExistAndNotExpire(BasicConstant.STR_ONE, BasicConstant.USER_NAME, 10000);
        // 没有
        Assertions.assertTrue(!cacheExistAndNotExpire);
        // 有
        cacheExistAndNotExpire = ObjectCache.checkCacheExistAndNotExpire(BasicConstant.STR_ONE, BasicConstant.USER_NAME, 10000);
        Assertions.assertTrue(cacheExistAndNotExpire);
        Thread.sleep(10000);
        // 没有
        cacheExistAndNotExpire = ObjectCache.checkCacheExistAndNotExpire(BasicConstant.STR_ONE, BasicConstant.USER_NAME, 10000);
        Assertions.assertTrue(!cacheExistAndNotExpire);
    }

}