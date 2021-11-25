package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import com.jef.constant.BasicEntity;
import com.jef.constant.BasicList;
import com.jef.entity.User;
import com.jef.util.StringUtils;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * 将T映射为R（转换功能）
 * @author Jef
 * @date 2021/2/20
 */
public class FunctionTest {

    @Test
    public void testApply() {
        User user = BasicEntity.getUser();
        Function<User, String> function = User::getName;
        String name = function.apply(user);
        System.out.println(name);
        Function<String, Integer> toInteger = Integer::valueOf;
        Integer integerValue = toInteger.apply("456");
        System.out.println("数字=" + integerValue);
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        String result = backToString.apply("123");
        StringUtils.printString(result); // "123"

        Function<String, Integer> f2 = String::length;
        Integer length = f2.apply(BasicConstant.HELLO_WORLD);
        System.out.println("字符串长度=" + length);

        // 同groupBy分组功能一样和toMap功能类似
        List<User> userList = BasicList.getUserList();
        Function<User, String> functionToMap = t -> t.getName() + t.getPhone();
        HashMap<String, User> namePhoneMap = Maps.newHashMap();
        userList.forEach(t -> namePhoneMap.put(functionToMap.apply(t), t));
        System.out.println("namePhoneMap Size=" + namePhoneMap.size());
    }
}
