package com.jef.collection;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 初始化map
 *
 * @author Jef
 * @date 2019/3/13
 */
public class MapTest {

    @Test
    public void testMapInit() {
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        map1.put("keyN", "valueN");
        System.out.println(JSONObject.toJSONString(map1));


        // 初始化Map2
        Map<String, String> map2 = new HashMap<String, String>() {
            private static final long serialVersionUID = -2776966410444286355L;

            {
                put("key1", "value1");
                put("key2", "value2");
                put("keyN", "valueN");
            }
        };
        System.out.println(JSONObject.toJSONString(map2));

        // 第三种, stream初始化
        Map map = Stream.of(1, 2, 3, 4).collect(Collectors.toMap(o -> o, integer -> integer, (o, o2) -> o2, HashMap::new));
        System.out.println(JSONObject.toJSONString(map));

        // 第四种
        Map map4 = ImmutableMap.of("ke1", 1, "key2", 2);
        System.out.println(JSONObject.toJSONString(map4));

    }

    /**
     * 性能对比
     */
    @Test
    public void testSpeed() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            HashMap<String, String> map = new HashMap<String, String>() {
                private static final long serialVersionUID = -7352911313876836169L;

                {
                    put("name", "test");
                    put("age", "20");
                }
            };
        }
        // 1217
        System.out.println("文艺写法用时=" + (System.currentTimeMillis() - st));
        st = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("name", "test");
            map.put("age", "20");
        }
        System.out.println("普通写法用时=" + (System.currentTimeMillis() - st)); // 1064
    }
}