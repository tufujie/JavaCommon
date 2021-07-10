package com.jef.dubbo.consumer;

import com.jef.dubbo.api.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo消费者2
 * @author Jef
 * @date 2021/3/4
 */
public class Consumer {
    public static void main(String[] args) {
        // 测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");
        context.start();
        System.out.println("消费者2号 开始消费...");
        DemoService demoService = context.getBean(DemoService.class);
        System.out.println("消费者2号 获取权限" + demoService.getPermissions(2L));
        System.out.println("消费者2号 结束消费...");
    }
}
