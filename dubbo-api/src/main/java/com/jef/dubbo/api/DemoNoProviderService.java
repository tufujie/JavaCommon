package com.jef.dubbo.api;

import com.jef.dubbo.entity.User;

/**
 * dubbo接口
 *
 * @author Jef
 * @date 2021/4/26
 */
public interface DemoNoProviderService {

    /**
     * 通过用户ID获取用户信息，模拟数据库交互
     *
     * @param id 用户ID
     * @return com.jef.dubbo.entity.User
     * @author Jef
     * @date 2021/9/13
     */
    User getByID(String id);
    
}
