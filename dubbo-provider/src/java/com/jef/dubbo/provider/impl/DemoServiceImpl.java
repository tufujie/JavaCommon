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
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }

    @Override
    public User getByID(String id) {
        User user = new User();
        user.setName("Jef");
        user.setPhone("13266860001");
        return user;
    }
}
