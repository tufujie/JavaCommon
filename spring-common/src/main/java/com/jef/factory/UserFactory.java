package com.jef.factory;

import com.jef.constant.BasicConstant;
import com.jef.entity.OrderInfo;
import com.jef.entity.User;

/**
 * @author Jef
 * @date 2020/11/29
 */
public class UserFactory {

    private User user;

    // a private constructor
    private UserFactory() {
        new UserFactory();
    }

    public static UserFactory createInstance(OrderInfo orderInfo) {
        UserFactory userFactory = new UserFactory();
        // 一些其他操作
        return userFactory;
    }
}