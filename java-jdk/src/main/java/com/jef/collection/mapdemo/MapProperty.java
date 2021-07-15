package com.jef.collection.mapdemo;

import com.google.common.collect.Maps;
import com.jef.util.LogicUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * Map属性值及使用
 * @author Jef
 * @date 20180727
 */
public class MapProperty {

    private static Map<String, String> rentPurposeMap = null;

    private static  final Object LOCK = new Object();

    public static void main(String[] args) {
        Map<String, Object> rentPurposeMapOne = RENT_PURPOSE_MAP;
        System.out.println(rentPurposeMapOne);
        // 第一次调用时为空
        System.out.println("first get");
        Map<String, String> rentPurposeMap = getRentPurpose();
        System.out.println(rentPurposeMap);
        System.out.println("first end");
        // 第二次调用时直接取出
        System.out.println("second get");
        rentPurposeMap = getRentPurpose();
        System.out.println(rentPurposeMap);
        System.out.println("second end");

    }


    /**
     * 方式1获取租赁用途map
     */
    public static final Map<String, Object> RENT_PURPOSE_MAP = new HashMap<String, Object>() {
        {
            put("1", "办公");
            put("2", "餐饮");
            put("3", "学校");
            put("4", "政府");
        }
    };

    /**
     * 方式2获取租赁用途map，建议
     * @return
     */
    public static Map<String, String> getRentPurpose() {
        if (LogicUtils.isNotNullAndEmpty(rentPurposeMap)) {
            return rentPurposeMap;
        }
        synchronized (LOCK) {
            if (LogicUtils.isNullOrEmpty(rentPurposeMap)) {
                // 跟添加顺序一致
                rentPurposeMap = Maps.newLinkedHashMap();
                rentPurposeMap.put("1", "办公");
                rentPurposeMap.put("2", "餐饮");
                rentPurposeMap.put("3", "学校");
                rentPurposeMap.put("4", "政府");
            }
        }
        return rentPurposeMap;
    }
}

