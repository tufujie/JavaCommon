package com.jef.dao;

import com.jef.dto.RequestParamDto;
import com.jef.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户DAO层
 *
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface IUserDao extends IBaseDao {
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

    User getByNameAndPassWord(User user);

    void insert(User user);

    User getByName(String name);

    List<User> getAllUser();

    void updateUser(User user);

    /**
     * 分表获取用户信息
     * @author Jef
     * @date 2019/4/4
     * @param requestParams
     * @return com.jef.entity.User
     */
    User getUserByParams(Map<String, Object> requestParams);

    Boolean insertSubUser(User user);

    Long getMaxUserID(Map<String, Object> requestParams);

    List<User> getUserList(Map<String, Object> requestParams);

    List<User> getUserListV2(RequestParamDto param);
}
