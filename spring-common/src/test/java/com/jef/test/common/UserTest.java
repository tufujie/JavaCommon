package com.jef.test.common;

import com.jef.common.context.SpringContextHolder;
import com.jef.entity.User;
import com.jef.service.impl.UserServiceImpl;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 测试获取用户信息
 *
 * @author Jef
 * @date 2022/1/12
 */
public class UserTest extends BaseTest {

    @Override
    public void init() throws Exception {

    }

    /**
     * 获取用户信息
     * 模拟service调用
     *
     * @date 2022/01/12
     */
    @Test
    public void testGetUser() throws Exception {
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(UserServiceImpl.class.getSimpleName(), UserServiceImpl.class);
        UserServiceImpl userService = SpringContextHolder.getBean(UserServiceImpl.class.getSimpleName());

        List<User> allUser = userService.getAllUser();
        System.out.println("取到的第一个用户名" + allUser.get(0).getName());
    }


}