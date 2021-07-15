package com.jef.jdk8;

import com.jef.constant.BasicList;
import com.jef.entity.User;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 使用两种方式计算平均年龄
 *
 * @author Jef
 * @date 2019/3/14
 */
public class ComputeAvg {

    public static void main(String[] args) {
        test();
        test2();
    }

    /**
     * jdk及以下计算平均年龄
     */
    public static void test() {
        List<User> userList = BasicList.getUserListV2();
        Integer sum = 0;
        Integer count = 0;
        for (User user : userList) {
            if (user.getAge() != null) {
                sum += user.getAge();
                count++;
            }
        }
        double avg = (double)sum / count;
        System.out.println("方式1平均值=" + avg);
    }

    /**
     * jdk8计算平均年龄
     */
    public static void test2() {
        List<User> userList = BasicList.getUserListV2();
        double avg = userList.stream().map(User::getAge).filter(Objects::nonNull).collect(Collectors.averagingDouble(Integer::doubleValue));
        System.out.println("方式2平均值=" + avg);
    }


}