package com.jef.demo.impl;

import com.jef.demo.api.UserServiceApi;
import com.jef.demo.dao.IUserDao;
import com.jef.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tufujie
 * @date 2023/8/14
 */
@Service
public class UserServiceImpl implements UserServiceApi {
    @Autowired
    private IUserDao userDao;


    @Override
    public User getByUser(User user) {
        return userDao.getByUser(user);
    }
}