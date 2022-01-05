package com.jef.test;

import com.jef.constant.BasicConstant;
import com.jef.dao.UserDao;
import com.jef.entity.User;
import com.jef.util.BeanMapUtil;
import com.jef.util.ConnectSessionUtil;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户测试类
 * @author Jef
 * @create 2018/5/15 19:25
 */
public class UserTest {
    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);

    /**
     * 测试通过id获取用户信息
     */
    @Test
    public void testGetUserById() {
        logger.info("测试通过id获取用户信息开始，待传入id={}", BasicConstant.ID);
        SqlSession session = ConnectSessionUtil.getSqlSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.selectByPrimaryKey(BasicConstant.ID);
        System.out.println("用户名=" + user.getName());
    }

    /**
     * 测试通过唯一键获取用户信息
     */
    @Test
    public void testGetUserByNameAndPhone() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("name", BasicConstant.USER_NAME);
        requestParams.put("phone", BasicConstant.USER_PHONE);
        UserDao userDao = session.getMapper(UserDao.class);
        User user = userDao.getByNameAndPhone(requestParams);
        System.out.println("用户名map=" + user.getName());
        // 等效于这种方式，推荐使用
        User newUser = new User();
        newUser.setName(BasicConstant.USER_NAME);
        newUser.setPhone(BasicConstant.USER_PHONE);
        // 调用mapBuild自动装配，指定dao层说明的范型
        Map<String, Object> map = new HashMap<>();
        BeanMapUtil.mapBuild(newUser, map);
        user = userDao.getByNameAndPhone(map);
        System.out.println("用户名javaBeanBuildMap=" + user.getName());
    }

    /**
     * 测试通过用户信息获取用户信息
     */
    @Test
    public void testGetUserByUser() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        user.setPhone(BasicConstant.USER_PHONE);
        UserDao userDao = session.getMapper(UserDao.class);
        User userDb = userDao.getByUser(user);
        System.out.println("用户名=" + userDb.getName());
    }

}
