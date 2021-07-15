package com.jef.reflection;

import com.jef.constant.BasicConstant;
import com.jef.constant.BasicEntity;
import com.jef.entity.User;
import com.jef.io.blog.nio.FileChannelInTest;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射测试
 * @author Jef
 * @date 2021/7/2
 */
public class ReflectionTest {

    @Test
    public void testReflectEntity() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = BasicEntity.getUser();
        Class clazz = user.getClass();
        Method method = clazz.getDeclaredMethod("get" + "Name");
        Object nameValue = method.invoke(user);
        System.out.println("nameValue=" + nameValue);
    }
}