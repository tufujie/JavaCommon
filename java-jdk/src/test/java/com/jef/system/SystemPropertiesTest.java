package com.jef.system;

import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SystemPropertiesTest {

    @Test
    public void testGetAllProperties() {
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> property : properties.entrySet()) {
            System.out.println(property.getKey() + ":" + property.getValue());
        }
        PrintUtil.printSplitLine();
        String apolloMeta = System.getProperty("apollo.meta");
        System.out.println("获取阿波罗配置=" + apolloMeta);
    }

    @Test
    public void testGetEnvproperties() {
        for (Map.Entry entry : System.getenv().entrySet())
            System.out.println(entry.getKey() + "=" + entry.getValue());
    }

    /**
     * 获取VM options内容
     *
     * @author Jef
     * @date 2022/1/5
     */
    @Test
    public void testGetVMOptions() {
        RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = runtimeMxBean.getInputArguments();
        System.out.println(arguments);
    }


}
