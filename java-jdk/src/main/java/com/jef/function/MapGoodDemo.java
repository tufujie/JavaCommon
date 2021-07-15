package com.jef.function;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jef on 2017-06-30.
 */
public class MapGoodDemo {
    public static void main(String[] args) {
        for(Map.Entry entry : paramMap1.entrySet()) {

        }
    }


    // 一些定死的数据可以放进里面，通过匹对来拿值
    public static final Map<String, Object> paramMap1 = new HashMap<String, Object>() {
        {
            put("extraOrderId", "12345");
            put("fromCountry", "CN");
        }
    };

}
