package com.jef.dubbo.consumer;

import com.jef.dubbo.api.DemoService;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * dubbo消费者1
 * @author Jef
 * @date 2021/3/4
 */
public class Consumer {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws IOException, InterruptedException {
       startConsumerV2();
    }

    /**
     * 启动生产者的方式1
     * @throws IOException
     */
    public static void startConsumer() throws IOException {
        // 测试常规服务
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("消费者1号 开始消费...");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("消费者1号 获取权限" + demoService.getPermissions(1L));
        System.out.println("消费者1号 结束消费...");
    }

    /**
     * 启动生产者的方式2
     * @throws IOException
     */
    public static void startConsumerV2() throws InterruptedException {
        ReferenceConfig<DemoService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(DemoService.class);
        DemoService service = reference.get();
        System.out.println("消费者1号 开始消费...");
        System.out.println("消费者1号 获取权限" + service.getPermissions(1L));
        System.out.println("消费者1号 结束消费...");
    }
}
