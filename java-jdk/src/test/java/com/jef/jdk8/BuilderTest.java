package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import com.jef.entity.User;

import org.junit.Test;

/**
 * @author Jef
 * @date 2021/7/4
 */
public class BuilderTest {

    @Test
    public void testUseBuildier() {
        User user = Builder.of(User::new)
                .with(User::setName, BasicConstant.USER_NAME)
                .with(User::setPhone, BasicConstant.USER_PHONE)
                .build();
        System.out.println("name=" + user.getName());
        System.out.println("phone=" + user.getPhone());
    }

}