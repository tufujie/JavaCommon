package com.jef.dubbo.provider.impl;

import com.jef.dubbo.api.DemoService;
import com.jef.dubbo.util.DubooUtil;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.RegistryFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * dubbo服务
 * @author Jef
 * @date 2021/3/4
 */
public class ProviderTest {

    /**
     * 启动生产者的方式1
     * @throws IOException
     */
    @Test
    public void testStartProvider() throws IOException {
        ClassPathXmlApplicationContext context = DubooUtil.getProviderContext();
        System.out.println("dubbo服务已经启动...");
        // 按任意键退出
        System.in.read();
    }

    /**
     * 启动生产者的方式2
     * @throws IOException
     */
    @Test
    public void testStartProviderV2() throws InterruptedException {
        // 服务实现
        DemoService demoService = new DemoServiceImpl();

        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("first-dubbo-provider");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://" + DubooUtil.ZOOKEEPER_ADDRESS + ":2181");
/*        registry.setUsername("aaa");
        registry.setPassword("bbb");*/

        // 服务提供者协议配置
        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setPort(20880);
        protocol.setThreads(200);

        // 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

        // 服务提供者暴露服务配置
        // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        ServiceConfig<DemoService> service = new ServiceConfig<DemoService>();
        service.setApplication(application);
        service.setRegistry(registry); // 多个注册中心可以用setRegistries()
        service.setProtocol(protocol); // 多个协议可以用setProtocols()
        service.setInterface(DemoService.class);
        service.setRef(demoService);
//        service.setVersion("1.0.0");

        // 暴露及注册服务
        service.export();
        System.out.println("dubbo服务已经启动...");
        new CountDownLatch(1).await();
    }

    /**
     * 注册工厂测试
     *
     * @author Jef
     * @date 2021/10/11
     */
    @Test
    public void testRegistryFactory() {
        RegistryFactory registryFactory = ExtensionLoader.getExtensionLoader(RegistryFactory.class).getAdaptiveExtension();
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://127.0.0.1:2181"));
        registry.register(URL.valueOf("memcached://127.0.0.1/com.jef.dubbo.api.DemoService?category=providers&dynamic=false&application=demotest-provider"));
    }

}

