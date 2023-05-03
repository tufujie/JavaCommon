package com.jef.collection;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jef
 * @date 2023/5/3
 */
public class CollectionsTest {

    @Test
    public void syncthronizedMapTest() {
        Map<String, Object> hashMap = new HashMap<>();
        Map<String, Object> map = Collections.synchronizedMap(hashMap);
        map.put("key", "value");
        System.out.println(map);
    }

}