package com.jef.collection.mapdemo;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 故意将key的值与value的值不一样，看排序是按照key排序还是value的值排序
 * HashMap結果，//随机的，此处看不出来
 * key: 1值为：1
 * key: 2值为：7
 * key: 3值为：3
 * key: 4值为：4
 * key: 7值为：2
 * TreeMap結果，//可以看到是按照key值排序的，默认升序
 * key: 1值为：1
 * key: 2值为：7
 * key: 3值为：3
 * key: 4值为：4
 * key: 7值为：2
 * LinkedHashMap結果，//按照插入的顺序排序
 * key: 1值为：1
 * key: 3值为：3
 * key: 7值为：2
 * key: 2值为：7
 * key: 4值为：4
 * @author Jef
 * @date 2019/7/10
 */
public class MapDemo {
    public static void main(String[] args) {
        HashMap<String, List> test = Maps.newHashMap();
        List<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        test.put("status", statusList);
        // 使用
        System.out.println(statusList.size());
        statusList.clear();
        // 重新使用
        statusList.add(3);
        // 覆盖已有key对应的内容
        test.put("status", statusList);
        // 遍历map
        System.out.println("第一种方式遍历");
        for (Map.Entry<String, List> obj : test.entrySet()) {
            System.out.println(obj.getKey() + "=" + obj.getValue());
        }
        // 遍历map
        System.out.println("第二种方式遍历");
        for (String obj : test.keySet()) {
            System.out.println(obj + "=" + test.get(obj));
        }
        // 遍历map
        System.out.println("第三种方式遍历");
        for (List<String> obj : test.values()) {
            System.out.println(obj);
        }
    }

    /**
     * 获取map中第一个key值
     * @param map 数据源
     * @return
     */
    private static String getKeyOrNull(Map<String, Object> map) {
        String obj = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            obj = entry.getKey();
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }


    /**
     * 获取map中第一个数据值
     * @param map 数据源
     * @return
     */
    private static Object getFirstOrNull(Map<String, Object> map) {
        Object obj = null;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return  obj;
    }

}
