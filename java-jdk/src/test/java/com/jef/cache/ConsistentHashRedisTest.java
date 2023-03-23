package com.jef.cache;

import com.jef.constant.BasicConstant;
import com.jef.util.ConsistentHashRedis;
import org.junit.jupiter.api.Test;

public class ConsistentHashRedisTest {

    @Test
    public void testConsistentHashRedisSetAndGet() {
        ConsistentHashRedis consistentHashRedis = new ConsistentHashRedis("127.0.0.1,127.0.0.2");
        String key = BasicConstant.LOGIN_OBJECT_KEY;
        consistentHashRedis.set(key, BasicConstant.USER_NAME);
        String value = consistentHashRedis.get(key);
        System.out.println("value=" + value);
    }
}
