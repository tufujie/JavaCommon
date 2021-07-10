package com.jef.jdk8;

import com.jef.constant.BasicList;
import com.jef.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * filter方法demo
 * @author Jef
 * @date 2019/3/14
 */
public class FilterDemo {
    public static void main(String[] args) {
        List<User> userList = BasicList.getUserList();
        Map<String, List<Long>> phoneIDListMap = userList.stream().filter(Objects::nonNull)
                .filter(user -> user.getPhone() != null)
                .collect(Collectors.groupingBy(User::getPhone, Collectors.mapping(User::getId, Collectors.toList())));
        System.out.println(phoneIDListMap);
    }
}