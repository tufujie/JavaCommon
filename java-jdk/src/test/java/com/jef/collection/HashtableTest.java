package com.jef.collection;

import com.jef.constant.BasicConstant;

import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;

/**
 * ConcurrentHashMap 示例
 *
 * @author Jef
 * @date 2022/2/22
 */
public class HashtableTest {

    @Test
    public void testHashtable() {
        Map<String, String> hashtable = new Hashtable<>(6);
        hashtable.put("userName", BasicConstant.USER_NAME);
        System.out.println(hashtable);
        String userName = hashtable.get("userName");
        System.out.println("userName=" + userName);
    }

}