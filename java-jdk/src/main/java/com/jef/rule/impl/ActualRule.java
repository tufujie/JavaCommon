package com.jef.rule.impl;

import com.jef.entity.User;
import com.jef.rule.AbstractRule;

/**
 * @author tufujie
 * @date 2023/8/24
 */
public class ActualRule extends AbstractRule {

    @Override
    public void handle(Object obj) {
        User user = (User) obj;
        System.out.println("处理一些业务，用户名=" + user.getName());
    }
}