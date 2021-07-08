package com.jef.dubbo.api;

import com.jef.dubbo.entity.User;

import java.util.List;

/**
 * dubbo接口
 * @author Jef
 * @date 2021/4/26
 */
public interface DemoService {
    List<String> getPermissions(Long id);

    User getByID(String id);
}
