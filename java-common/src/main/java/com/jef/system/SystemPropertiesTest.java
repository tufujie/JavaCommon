package com.jef.system;

import java.util.Map;
import java.util.Properties;

public class SystemPropertiesTest {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        for(Map.Entry<Object, Object> property : properties.entrySet()) {
            System.out.println(property.getKey() + ":" + property.getValue());
        }
    }
}
