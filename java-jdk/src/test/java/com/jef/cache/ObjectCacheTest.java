package com.jef.cache;

import com.jef.constant.BasicConstant;
import com.jef.constant.BasicEntity;
import com.jef.entity.User;
import com.jef.redis.RedisServiceFactory;
import com.jef.redis.cache.ObjectCache;
import com.jef.util.SpringPropertiesUtil;
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
        // 默认16个库中的第一个库，可以设置存储的库，从0开始
//        conn.setDatabase(1);
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

        // 集群设置
        SpringPropertiesUtil.setProperty("redis.diffdb", "true");
        SpringPropertiesUtil.setProperty("jdbc.url", "127.0.0.1/app");

    }

    @BeforeEach
    public void init() throws NoSuchFieldException, IllegalAccessException {
        initRedis();
    }

    @Test
    public void testSetCache() {
        User user = BasicEntity.getUser();
        ObjectCache.setCache(BasicConstant.LOGIN_OBJECT_KEY, user.getPhone(), user, false);
    }

    @Test
    public void testGetCache() {
        User user = (User) ObjectCache.getCache(BasicConstant.LOGIN_OBJECT_KEY, BasicConstant.USER_PHONE);
        Assertions.assertTrue(BasicConstant.USER_NAME.equals(user.getName()));
    }

    @Test
    public void testCheckCacheExistAndNotExpire() throws InterruptedException {
        String id = "test", key = BasicConstant.USER_NAME_KEY + id;
        boolean cacheExistAndNotExpire = ObjectCache.checkCacheExistAndNotExpire(key, id, 2000);
        Assertions.assertFalse(cacheExistAndNotExpire);
        // 二次进入，有缓存，为true，此时可以用来防止二次进入
        cacheExistAndNotExpire = ObjectCache.checkCacheExistAndNotExpire(key, id, 2000);
        Assertions.assertTrue(cacheExistAndNotExpire);
        Thread.sleep(3000);
        // 缓存过期
        cacheExistAndNotExpire = ObjectCache.checkCacheExistAndNotExpire(key, id, 2000);
        Assertions.assertFalse(cacheExistAndNotExpire);
    }

}