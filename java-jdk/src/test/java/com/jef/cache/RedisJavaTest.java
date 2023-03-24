package com.jef.cache;

import com.jef.constant.BasicConstant;
import com.jef.redis.RedisJavaUtil;
import com.jef.util.PrintUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author Jef
 */
public class RedisJavaTest {

    /**
     * Redis Java String(字符串) 实例
     */
    @Test
    public void testSetAndGetString() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        // 设置 redis 字符串数据
        jedis.set(BasicConstant.USER_NAME_KEY, BasicConstant.USER_NAME);
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get(BasicConstant.USER_NAME_KEY));
    }

    /**
     * Redis Java List(列表) 实例
     */
    @Test
    public void testSetAndGetList() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        // 存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }

    /**
     * Redis Java Set(集合) 实例
     */
    @Test
    public void testGetSetAndGetSet() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        jedis.sadd("site-set", "Runoob");
        jedis.sadd("site-set", "Google");
        jedis.sadd("site-set", "Taobao");
        // 获取存储的数据并输出
        Set<String> list = jedis.smembers("site-set");
        System.out.println(list);
    }

    /**
     * Redis Java Hash(哈希) 实例
     */
    @Test
    public void testGetSetAndGeHash() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        jedis.hset(BasicConstant.LOGIN_OBJECT_KEY, BasicConstant.USER_NAME_KEY, BasicConstant.USER_NAME);
        String userName = jedis.hget(BasicConstant.LOGIN_OBJECT_KEY, BasicConstant.USER_NAME_KEY);
        Assertions.assertTrue(BasicConstant.USER_NAME.equals(userName));
    }

    /**
     * Redis Java SortedSet(有序集合) 实例
     */
    @Test
    public void testGetSetAndGeZSet() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        jedis.zadd(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 10, "Java");
        jedis.zadd(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 9, "Python");
        jedis.zadd(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 8, "GO");
        jedis.zadd(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 7, "C++");
        long rankSetSize = jedis.zcard(BasicConstant.PROGRAMMING_LANGUAGE_RANK);
        System.out.println("rankSetSize=" + rankSetSize);
        PrintUtil.printSplitLine();

        Set<Tuple> rankRevSet = jedis.zrevrangeWithScores(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 0L, 2L);
        System.out.println("正排行=" + rankRevSet);
        Set<Tuple> rankSet = jedis.zrangeWithScores(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 0L, 2L);
        System.out.println("逆排行=" + rankSet);
        // 其中有序集成员按分数值递增(从小到大)顺序排列。也就是说数值越大，返回的索引越大
        long javaRankIndex = jedis.zrank(BasicConstant.PROGRAMMING_LANGUAGE_RANK, "Java");
        long javaRank = rankSetSize - javaRankIndex;
        System.out.println("一开始的Java排行=" + javaRank);
        PrintUtil.printSplitLine();

        System.out.println("Python分数加2");
        jedis.zincrby(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 2, "Python");
        rankRevSet = jedis.zrevrangeWithScores(BasicConstant.PROGRAMMING_LANGUAGE_RANK, 0L, 2L);
        System.out.println("正排行=" + rankRevSet);
        javaRankIndex = jedis.zrank(BasicConstant.PROGRAMMING_LANGUAGE_RANK, "Java");
        javaRank = rankSetSize - javaRankIndex;
        System.out.println("然后的Java排行=" + javaRank);
    }


    /**
     * Redis Java Keys 实例
     */
    @Test
    public void testGetAllKeys() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        // 获取Jedis中的所有key数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }

    /**
     * Redis递增
     */
    @Test
    public void testIncr() {
        String key = "incrKey";
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        long num = jedis.incr(key);
        System.out.println("incrKey=" + num);
        num = jedis.incr(key);
        System.out.println("incrKey=" + num);
    }

    /**
     * Redis递减
     */
    @Test
    public void testDecr() {
        String key = "decrKey";
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        jedis.set(key, "10");
        long num = jedis.decr(key);
        System.out.println("decrKey=" + num);
        num = jedis.decr(key);
        System.out.println("decrKey=" + num);
    }

    /**
     * Redis删除
     */
    @Test
    public void testDel() {
        Jedis jedis = RedisJavaUtil.getAuthJedis();
        jedis.hset(BasicConstant.LOGIN_OBJECT_KEY, BasicConstant.USER_NAME_KEY, BasicConstant.USER_NAME);
        String userName = jedis.hget(BasicConstant.LOGIN_OBJECT_KEY, BasicConstant.USER_NAME_KEY);
        System.out.println("userName=" + userName);
        jedis.del(BasicConstant.LOGIN_OBJECT_KEY);
        userName = jedis.hget(BasicConstant.LOGIN_OBJECT_KEY, BasicConstant.USER_NAME_KEY);
        System.out.println("userName=" + userName);
    }

}
