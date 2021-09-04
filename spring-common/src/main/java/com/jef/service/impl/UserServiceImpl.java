package com.jef.service.impl;

/*import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;*/
import com.google.common.collect.Maps;
import com.jef.dao.IUserDao;
import com.jef.dbRouting.annotation.Router;
import com.jef.dto.RequestParamDto;
import com.jef.entity.User;
import com.jef.helper.PageCustomHelper;
import com.jef.property.cache.UserCache;
import com.jef.service.IUserService;
import com.jef.common.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务
 *
 * @author Jef
 * @create 2018/5/15 19:21
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public User getByNameAndPassWord(User user) {
        return userDao.getByNameAndPassWord(user);
    }

    @Override
    public User insert(User user) {
        user.setPassword(MD5Util.encode(user.getPassword()));
        userDao.insert(user);
        return user;
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userDao.updateUser(user);
        UserCache.clearCache(user.getId());
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public User insertSubUser(User user) {
        user.setPassword(MD5Util.encode(user.getPassword()));
        userDao.insertSubUser(user);
        return user;
    }

    @Override
    public Long getMaxUserID() {
        Map<String, Object> requestParams = Maps.newHashMap();
        return userDao.getMaxUserID(requestParams);
    }

    @Override
    public List<User> getUserList(Map<String, Object> requestParams) {
        return null;
    }

    @Override
    public List<User> query(Map<String, Object> queryMap, int startPageNum, int pageCountNum) throws Exception {
        PageCustomHelper.startPage(startPageNum, pageCountNum);
        List<User> userList = userDao.getUserList(queryMap);
        return userList;
    }

/*    @Override
    public PageInfo<User> queryV2(RequestParamDto param) throws Exception {
        PageHelper.startPage(param.getPageNum(), param.getPageSize());
        List<User> list = userDao.getUserListV2(param);
        PageInfo<User> pageInfo = (PageInfo<User>)list;
        return pageInfo;
    }*/

    @Override
    public User getUserByID(Long userID) throws Exception {
        return UserCache.getUser(userDao, userID);
    }
}
