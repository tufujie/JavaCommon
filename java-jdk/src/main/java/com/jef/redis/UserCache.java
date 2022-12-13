package com.jef.redis;

import com.jef.entity.User;

/**
 * @author Administrator
 * @date 2022/12/12
 */
public class UserCache {

    public static User getUserByID(Long id) {
        System.out.println("通过缓存取值，用户id=" + id);
        User user = new User(id);
        // 模拟从缓存取值
        user.setName(id + "testName");
        return user;
    }
}