package com.jef.service;

//import com.github.pagehelper.PageInfo;
import com.jef.dto.RequestParamDto;
import com.jef.entity.User;
import com.jef.property.cache.UserCache;

import java.util.List;
import java.util.Map;

public interface IUserService {

    /**
     * 通过用户名和密码获取用户
     * @param name
     * @param password
     * @return
     */
    User getByNameAndPassWord(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    User insert(User user);

    User getByName(String name);

    void updateUser(User user) throws Exception;

    List<User> getAllUser();

    /**
     * 新增用户
     * @param user 用户信息
     * @return 用户信息
     */
    User insertSubUser(User user);

    /**
     * 获取最大的用户ID
     * @return 最大的用户ID
     */
    Long getMaxUserID();

    List<User> getUserList(Map<String, Object> requestParams);

    /**
     * 查询客户列表
     * @author Jef
     * @date 2020/8/27
     * @param queryMap
     * @param startPageNum
     * @param pageCountNum
     * @return java.util.List<com.jef.entity.User>
     */
    List<User> query(Map<String, Object> queryMap, int startPageNum, int pageCountNum) throws Exception;
    /**
     * 查询客户列表
     * @author Jef
     * @date 2020/8/27
     * @return java.util.List<com.jef.entity.User>
     */
//    PageInfo<User> queryV2(RequestParamDto param) throws Exception;

    User getUserByID(Long userID) throws Exception;
}
