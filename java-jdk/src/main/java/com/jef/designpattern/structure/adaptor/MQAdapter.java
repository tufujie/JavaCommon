package com.jef.designpattern.structure.adaptor;

import com.jef.entity.MQInfo;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

/**
 * @author Jef
 * @date 2021/12/7
 */
public class MQAdapter {

    public static MQInfo filter(String strJson, Map<String, String> link) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        return filter(JSON.parseObject(strJson, Map.class), link);
    }

    public static MQInfo filter(Map obj, Map<String, String> link)
            throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException {
        MQInfo mqInfo = new MQInfo();
        for (String key : link.keySet()) {
            Object entityProperty = link.get(key);
            Object val = obj.get(entityProperty);
            String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
            if ("mqAdaptor".equals(key)) {
                val = entityProperty;
            } else if ("bizTime".equals(key)) {
                val = new Date(Long.valueOf(val.toString()));
                MQInfo.class.getMethod(methodName, Date.class).invoke(mqInfo, val);
                continue;
            } else {
                val = val.toString();
            }
            MQInfo.class.getMethod(methodName, String.class).invoke(mqInfo, val);
        }
        return mqInfo;
    }
}