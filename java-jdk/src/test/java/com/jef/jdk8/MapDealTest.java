package com.jef.jdk8;

import com.jef.constant.BasicConstant;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * map处理
 *
 * @author Jef
 * @date 2019/3/13
 */
public class MapDealTest {

    @Test
    public void testMapDeal() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", 1L);
        map.put("name", BasicConstant.USER_NAME);
        // 遍历map
        map.forEach((k, v) ->
                System.out.println("key : " + k + ";value : " + v)
        );
        // map转list
        List<Map<String, Object>> listMap = map
                .entrySet()
                .stream()
                .map(
                    e -> new HashMap<String , Object>(){{
                    put("test1", e.getKey());
                    put("test2", e.getValue());
        }}).collect(Collectors.toList());
        System.out.println(listMap);
    }
}