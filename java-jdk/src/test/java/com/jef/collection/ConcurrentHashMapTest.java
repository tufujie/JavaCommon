package com.jef.collection;

import com.jef.constant.BasicConstant;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap 示例
 *
 * @author Jef
 * @date 2022/2/22
 */
public class ConcurrentHashMapTest {

    @Test
    public void testConcurrentHashMap() {
        Map<String, String> concurrentHashMap = new ConcurrentHashMap<>(6);
        concurrentHashMap.put("userName", BasicConstant.USER_NAME);
        System.out.println(concurrentHashMap);
        String userName = concurrentHashMap.get("userName");
        System.out.println("userName=" + userName);
    }

}