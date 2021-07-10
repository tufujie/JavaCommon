package com.jef.dao;

import com.jef.entity.User;

import java.util.Map;

/**
 * 用户DAO层
 *
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface UserDao {
    /**
     * 根据ID查询用户信息
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 根据用户名称和手机号查询用户信息
     * @param requestParams
     * @return
     */
    User getByNameAndPhone(Map<String, Object> requestParams);

    User getByUser(User user);
}
