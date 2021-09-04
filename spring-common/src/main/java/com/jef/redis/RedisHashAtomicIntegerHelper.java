package com.jef.redis;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Jef
 * @date 2020/9/23
 */
public class RedisHashAtomicIntegerHelper extends Number implements Serializable {

    private static final long serialVersionUID = 1L;
    private String redisKey;
    private String hashKey;
    private Long liveTime = 3 * 86400000L;
    private final RedisOperations<String, HashMap> template;
    private final HashOperations<String, String, Integer> operations;

    public RedisHashAtomicIntegerHelper(String redisKey, String hashKey, RedisConnectionFactory factory) {
        this(redisKey, hashKey, (RedisConnectionFactory)factory, (Integer)null);
    }

    private RedisHashAtomicIntegerHelper(String redisKey, String hashKey, RedisConnectionFactory factory, @Nullable Integer initialValue) {
        RedisTemplate<String, HashMap> redisTemplate = new RedisTemplate();
//        redisTemplate.setKeySerializer(RedisSerializer.java());
//        redisTemplate.setHashKeySerializer(RedisSerializer.java());
        redisTemplate.setHashValueSerializer(new GenericToStringSerializer(Integer.class));
        redisTemplate.setExposeConnection(true);
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.afterPropertiesSet();
        this.redisKey = redisKey;
        this.hashKey = hashKey;
        this.template = redisTemplate;
        this.operations = this.template.opsForHash();
        if (initialValue == null) {
            this.initializeIfAbsent();
        } else {
            this.set(initialValue);
        }
        this.template.expire(this.redisKey, this.liveTime, TimeUnit.MILLISECONDS);
    }

    /**
     * @Description set expire time
     * @author Justin Teng
     * @date 2020/4/27
     * @param milliSeconds
     * @return void
     */
    public void setExpireTime(Long milliSeconds) {
        this.liveTime = milliSeconds;
        this.template.expire(this.redisKey, this.liveTime, TimeUnit.MILLISECONDS);
    }

    /**
     * @Description if redis does't contains the hashKey in the redisKey, this function will set its hashValue as zero
     * @author Justin Teng
     * @date 2020/4/27
     * @param
     * @return void
     */
    private void initializeIfAbsent() {
        byte[] rawKey = ((RedisSerializer<String>)this.template.getKeySerializer()).serialize(this.redisKey);
        byte[] rawHashKey = ((RedisSerializer<String>)this.template.getHashKeySerializer()).serialize(this.hashKey);
        byte[] rawHashValue = ((RedisSerializer<Integer>)this.template.getHashValueSerializer()).serialize(0);
        this.operations.getOperations().execute(
                (RedisCallback<Boolean>) (connection) -> connection.hSetNX(rawKey, rawHashKey, rawHashValue)
        );
    }

    /**
     * @Description this function will set the newValue as the hashValue of the hashKey in the redisKey
     * @author Justin Teng
     * @date 2020/4/27
     * @param newValue
     * @return void
     */
    private void set(int newValue) {
        this.operations.put(this.redisKey, this.hashKey, newValue);
    }

    /**
     * @Description get the hashValue of the hashKey in the redisKey
     * @author Justin Teng
     * @date 2020/4/27
     * @param
     * @return int
     */
    public int get() {
        Integer value = this.operations.get(this.redisKey, this.hashKey);
        if (value != null) {
            return value;
        } else {
            throw new DataRetrievalFailureException(String.format("The hashKey '%s' in redisKey '%s' seems to no longer exist.", this.hashKey, this.redisKey));
        }
    }

    /**
     * @Description get and increment
     * @author Justin Teng
     * @date 2020/4/27
     * @param
     * @return int
     */
    public int getAndIncrement(Integer plus) {
        return this.operations.increment(this.redisKey, this.hashKey, plus).intValue();
    }

    /**
     * @Description get and increment
     * @author Justin Teng
     * @date 2020/4/27
     * @param
     * @return int
     */
    public int getAndIncrement() {
        return this.getAndIncrement(1);
    }

    public void setLiveTime(long liveTime) {

    }

    @Override
    public int intValue() {
        return this.get();
    }

    @Override
    public long longValue() {
        return (long)this.get();
    }

    @Override
    public float floatValue() {
        return (float)this.get();
    }

    @Override
    public double doubleValue() {
        return (double)this.get();
    }


}