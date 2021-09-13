package com.jef.dubbo.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jef.dubbo.api.DemoService;
import com.jef.dubbo.entity.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class DemoServiceImpl implements DemoService {
    @Override
    public List<String> getPermissions(Long id) {
        System.out.println("消费者" + id + "号 开始消费获取权限...");
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        System.out.println("消费者" + id + "号 结束消费获取权限...");
        return demo;
    }

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
