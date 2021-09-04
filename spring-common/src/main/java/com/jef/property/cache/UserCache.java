package com.jef.property.cache;

import com.jef.dao.IUserDao;
import com.jef.entity.User;
import com.jef.redis.cache.ObjectCache;

/**
 * 用户缓存
 * @author Jef
 * @create 2018/7/13 11:25
 */
public class UserCache {

    /**
     * 获取用户，优先从缓存获取
     * 使用场景：
     * @date: 2016年4月26日 下午7:13:04
     * @param id
     * @return
     * @throws Exception
     */
    public static User getUser(IUserDao userDao, Long id) throws Exception {
        if (id == null) {
            return null;
        }
        // 先从缓存获取
        User user = (User)ObjectCache.getCache(User.OBJECT_KEY, String.valueOf(id));
        if (user == null) {
            // 数据库查询
            user = userDao.selectByPrimaryKey(id);
            //放到缓存中
            if (user != null) {
                ObjectCache.setCache(User.OBJECT_KEY, String.valueOf(id), user, false);
            }
        }
        return user;
    }

    public static void clearCache(Long id) throws Exception {
        ObjectCache.clearCache(User.OBJECT_KEY, String.valueOf(id));
    }

}
