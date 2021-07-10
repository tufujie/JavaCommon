package com.jef.util;

import com.jef.constant.BasicConstant;
import com.jef.entity.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jef
 * @date 2018/10/29 18:50
 */
public class PropertyUtilTest {

    @Test
    public void testTransToMap() {
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        String[] fields = {"name"};
        System.out.println(PropertyUtil.transToMap(fields, user));
    }

    @Test
    public void testTransToMapList() {
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        User userTwo = new User();
        userTwo.setName(BasicConstant.STR_ONE);
        List<User> users = Arrays.asList(user, userTwo);
        String[] fields = {"name"};
        System.out.println(PropertyUtil.transToMap(fields, users));
    }

}
