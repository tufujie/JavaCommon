package com.jef.dubbo.provider.impl;

import com.jef.dubbo.api.DemoNoProviderService;
import com.jef.dubbo.entity.User;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "1.0.1")
public class DemoNoProviderServiceImpl implements DemoNoProviderService {

    @DubboService(version = "1.0.2")
    @Override
    public User getByID(String id) {
        System.out.println("消费者" + id + "号 开始消费获取用户信息...");
        User user = new User();
        user.setName("Jef");
        user.setPhone("13266860001");
        System.out.println("消费者" + id + "号 结束消费获取用户信息...");
        return user;
    }
}
