package com.jef.collection.listdemo;

import com.google.common.collect.Maps;
import com.jef.constant.BasicList;
import com.jef.entity.User;
import com.jef.util.StringUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 集合分类处理
 * @author Jef
 * @date 2019/6/15
 */
public class ListKindDemo {

    public static void main(String[] args) {
        kindOne();
    }

    /**
     * 集合分类处理方式1
     */
    private static void kindOne() {
        List<User> userList = BasicList.getUserList();
        Map<Integer, List<User>> typeUserMap = Maps.newHashMap();
        // 分类方式1
        for (User user : userList) {
            Integer userTypeTemp = user.getType();
            if (typeUserMap.containsKey(userTypeTemp)) {
                typeUserMap.get(userTypeTemp).add(user);
            } else {
                typeUserMap.put(userTypeTemp, new ArrayList<User>(Collections.nCopies(1, user)));
            }
        }
        System.out.println(typeUserMap);
        // 分类方式2
        Map<Integer, List<User>> typeUserMapV2 = userList.stream().collect(Collectors.groupingBy(user -> user.getType()));
        System.out.println(typeUserMapV2);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Integer, List<User>> map : typeUserMap.entrySet()) {
            List<User> usersTemp = map.getValue();
            String typeTemp = "";
            for (User user : usersTemp) {
                if (StringUtils.isEmpty(typeTemp)) {
                    typeTemp += user.getType() + ":";
                } else {
                    typeTemp += ",";
                }
                typeTemp += user.getName();
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                stringBuilder.append(";");
            }
            stringBuilder.append(typeTemp);
        }
        System.out.println(stringBuilder);
    }

}