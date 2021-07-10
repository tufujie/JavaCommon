package com.jef.util;

import com.jef.constant.BasicConstant;
import com.jef.constant.BasicList;
import com.jef.entity.User;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author Jef
 * @date 2019/7/7
 */
public class CopyUtilTest {

    /**
     * Object
     */
    @Test
    public void testCloneV1() {
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        User user1  = new User();
        // 深拷贝，改变现对象的值不会影响之前的对象的属性
        BeanUtils.copyProperties(user, user1);
        user1.setName(BasicConstant.STR_ONE);
        UserUtil.printUserNameAndPhone(user);
        UserUtil.printUserNameAndPhone(user1);
    }

    /**
     * Object
     */
    @Test
    public void testCloneV2() {
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        // 深拷贝，改变现对象的值不会影响之前的对象的属性
        User user1 = CopyUtil.clone(user);
        user1.setName(BasicConstant.STR_ONE);
        UserUtil.printUserNameAndPhone(user);
        UserUtil.printUserNameAndPhone(user1);
    }


    /**
     * Object
     */
    @Test
    public void testCloneV3() throws IOException, ClassNotFoundException {
        List<User> userList = BasicList.getUserList();
        UserUtil.printUserNameAndPhone(userList);
        List<User> userListV2 = CopyUtil.deepCopy(userList);
        UserUtil.printUserNameAndPhone(userListV2);
    }

}