package com.jef.jdk8;

import com.jef.entity.User;
import com.jef.redis.UserCache;
import com.jef.util.StringUtils;

import java.util.Map;

/**
 * 表单创建人
 *
 * @author Administrator
 * @date 2022/12/12
 */
public class FormCreatorNameGetFromCache<T> extends BaseDataSetGetFromCache<T, String> {

    public FormCreatorNameGetFromCache() {

    }

    public FormCreatorNameGetFromCache(Map<String, String> userLocalMap) {
        super(userLocalMap);
    }

    @Override
    protected boolean isNullData(String s) {
        return StringUtils.isEmpty(s);
    }

    @Override
    protected String getDataFormCache(Long key) {
        User user = UserCache.getUserByID(key);
        if (user != null) {
            return user.getName();
        }
        return null;
    }
}