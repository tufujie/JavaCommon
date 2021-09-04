package com.jef.service.impl;

import com.jef.dao.IUserDao;
import com.jef.entity.User;
import com.jef.service.IDubboUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author Jef
 * @create 2018/5/15 19:21
 */
@Service(value = "dubboUserService")
public class DubboUserServiceImpl implements IDubboUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }
}
