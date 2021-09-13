package com.jef.dubbo.util;

import com.jef.dubbo.api.DemoService;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Jef
 * @date 2021/9/13
 */
public class DubooUtil {

    /**
     * zookeeper地址
     */
    public static String ZOOKEEPER_ADDRESS = System.getProperty("zookeeper.address", "127.0.0.1");

    /**
     * 获取消费者上下文
     * @author Jef
     * @date 2021/9/13
     * @return org.springframework.context.support.ClassPathXmlApplicationContext
     */
    public static ClassPathXmlApplicationContext getConsumerContext() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        return context;
    }

    /**
     * 获取消费者上下文
     * @author Jef
     * @date 2021/9/13
     * @return org.springframework.context.support.ClassPathXmlApplicationContext
     */
    public static ClassPathXmlApplicationContext getProviderContext() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        return context;
    }

    /**
     * 获取消费者接口配置
     * @author Jef
     * @date 2021/9/13
     * @return org.springframework.context.support.ClassPathXmlApplicationContext
     */
    public static ReferenceConfig<DemoService> getConsumerConfig() {
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("first-dubbo-consumer");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://" + DubooUtil.ZOOKEEPER_ADDRESS + ":2181");
/*        registry.setUsername("aaa");
        registry.setPassword("bbb");*/

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        ReferenceConfig<DemoService> reference = new ReferenceConfig<DemoService>();
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(DemoService.class);
//        reference.setVersion("1.0.0");
        return reference;

    }

}