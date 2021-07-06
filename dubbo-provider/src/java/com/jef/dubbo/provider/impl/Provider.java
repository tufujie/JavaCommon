package com.jef.dubbo.provider.impl;

import com.jef.dubbo.api.DemoService;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * dubbo服务
 * @author Jef
 * @date 2021/3/4
 */
public class Provider {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws IOException {
        startProvider();
    }

    /**
     * 启动生产者的方式1
     * @throws IOException
     */
    public static void startProvider() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("provider.xml");
        context.start();
        System.out.println("dubbo服务已经启动...");
        System.in.read();
    }

    /**
     * 启动生产者的方式2
     * @throws IOException
     */
    public static void startProviderV2() throws InterruptedException {
        ServiceConfig<DemoService> service = new ServiceConfig<>();
        service.setApplication(new ApplicationConfig("first-dubbo-provider"));
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        service.setInterface(DemoService.class);
        service.setRef(new DemoServiceImpl());
        service.export();
        System.out.println("dubbo服务已经启动...");
        new CountDownLatch(1).await();
    }

}

