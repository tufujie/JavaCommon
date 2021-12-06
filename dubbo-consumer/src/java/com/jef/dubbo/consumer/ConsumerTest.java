package com.jef.dubbo.consumer;

import com.jef.dubbo.api.DemoNoProviderService;
import com.jef.dubbo.api.DemoService;
import com.jef.dubbo.entity.User;
import com.jef.dubbo.util.DubooUtil;

import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.MethodConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * dubbo消费者1
 * @author Jef
 * @date 2021/3/4
 */
public class ConsumerTest {

    /**
     * 启动消费者的方式1
     * @throws IOException
     */
    @Test
    public void testStartConsumer() throws IOException {
        // 测试常规服务
        ClassPathXmlApplicationContext context = DubooUtil.getConsumerContext();
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("消费者1号 获取权限" + demoService.getPermissions(1L));
        User user = demoService.getByID("1");
        System.out.println("消费者2号 获取用户名称=" + user.getName());
        User userNameAndPhone = demoService.getByNameAndPhone("Jef", "13266860001");
        System.out.println("消费者3号 获取用户名称=" + userNameAndPhone.getName());
    }

    /**
     * 启动消费者的方式2
     * @throws IOException
     */
    @Test
    public void testStartConsumerV2() throws InterruptedException {
        ReferenceConfig<DemoService> reference = DubooUtil.getConsumerConfig();
        // 和本地bean一样使用demoService
        DemoService demoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        System.out.println("消费者1号 获取权限" + demoService.getPermissions(1L));
    }

    /**
     * 和本地服务一样使用远程服务
     * @throws IOException
     */
    @Test
    public void testUseService() throws IOException {
        // 测试常规服务
        ClassPathXmlApplicationContext context = DubooUtil.getConsumerContext();
        FunctionAction functionAction = context.getBean(FunctionAction.class);
        DemoService demoService = functionAction.getDemoService();
        System.out.println("消费者1号 获取权限" + demoService.getPermissions(1L));
        User user = demoService.getByID("1");
        System.out.println("消费者1号 获取用户名称=" + user.getName());
    }

    @Test
    public void testConfigCenterConfig() {
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setAddress("zookeeper://127.0.0.1:2181");

    }

    /**
     * 方法级配置
     * @author Jef
     * @date 2021/9/13
     * @param
     */
    @Test
    public void testMethodConfig() {
        ReferenceConfig<DemoService> reference = DubooUtil.getConsumerConfig();
        List<MethodConfig> methods = new ArrayList<MethodConfig>();
        MethodConfig method = new MethodConfig();
        method.setName("createXxx");
        method.setTimeout(10000);
        method.setRetries(0);
        methods.add(method);
        // 引用远程服务
        // 设置方法级配置
        reference.setMethods(methods);
    }

    @Test
    public void testPointToPoint() {
        ReferenceConfig<DemoService> reference = DubooUtil.getConsumerConfig();
        // 如果点对点直连，可以用reference.setUrl()指定目标地址，设置url后将绕过注册中心，
        // 其中，协议对应provider.setProtocol()的值，端口对应provider.setPort()的值，
        // 路径对应service.setPath()的值，如果未设置path，缺省path为接口名
        reference.setUrl("dubbo://127.0.0.1:20880/com.jef.dubbo.api.DemoService");
    }

    /**
     * 测试消费无生产者
     */
    @Test
    public void testStartConsumerNoProvider() {
        ClassPathXmlApplicationContext context = DubooUtil.getConsumerContext();
        DemoNoProviderService demoNoProviderService = context.getBean(DemoNoProviderService.class);
        User user = demoNoProviderService.getByID("1");
        System.out.println("消费者1号 获取用户名称=" + user.getName());
    }
}
