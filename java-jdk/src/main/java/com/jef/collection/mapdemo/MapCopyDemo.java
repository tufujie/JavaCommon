package com.jef.collection.mapdemo;

import com.jef.util.CopyUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * map深拷贝、浅拷贝
 * @author Jef
 * @date 2018/9/19 9:40
 */
public class MapCopyDemo {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(100);
        list.add(200);
        map.put("basicType", 100);
        map.put("list", list);
        // 数据展示
        System.out.println("------数据展示--------");
        System.out.println("map:\n" + map);

        // 此处想达到copyMap1数据为map中的数据不变
        System.out.println("浅复制。。。。。");
        // 浅复制，只是引用给了copyMap1,map中数据改变,copyMap1数据也会改变,copyMap1改变,map的数据也会改变
        Map<String, Object> copyMap1 = map;
        map.put("basicType", 200);
        System.out.println("map:\n" + map);
        System.out.println("copyMap1:\n" + copyMap1);
        map.put("basicTypeCopy", 200);
        System.out.println("map:\n" + map);
        System.out.println("copyMap1:\n" + copyMap1);


        // 此处想达到copyMap2数据为map中的数据不变
        System.out.println("假深复制-----改变引用类型和基本类型");
        Map<String, Object> copyMap2 = new HashMap<String, Object>();
        copyMap2.putAll(map);// 假深复制，只对基本类型可用，当list中内容发生改变时，copyMap2中数据依然会变
        map.put("basicType", 300);
        list.add(300);
        System.out.println("map:\n" + map);
        System.out.println("copyMap2:\n" + copyMap2);
        copyMap2.put("basicTypeCopy2", 300);
        System.out.println("map:\n" + map);
        System.out.println("copyMap2:\n" + copyMap2);

        // 此处想达到copyMap3数据为map中的数据不变
        System.out.println("深复制---使用序列化进行深拷贝");
        HashMap<String, Object> copyMap3 = CopyUtil.clone(map);
        map.put("basicType", 400);
        list.add(400);
        System.out.println("map:\n" + map);
        System.out.println("copyMap3:\n" + copyMap3);

    }


}
