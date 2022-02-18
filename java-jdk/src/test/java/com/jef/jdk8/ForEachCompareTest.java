package com.jef.jdk8;

import com.jef.entity.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jef
 * @date 2022/2/15
 */
public class ForEachCompareTest {
    List<User> listUser = new ArrayList<>();

    @Before
    public void initList() {
        listUser = this.getListUsers();
    }

    @Test
    public void testForEachCompare() {
        // 普通的forEach
        long startSimpleTime = System.currentTimeMillis();
        for (User user : listUser) {
            user.toString();
        }
        long endSimpleTime = System.currentTimeMillis();
        System.out.println("Simple:" + (endSimpleTime - startSimpleTime));

        // java8中新的forEach
        long startLambda = System.currentTimeMillis();
        listUser.forEach(User::toString);
        long endLambda = System.currentTimeMillis();
        System.out.println("Lambda:" + (endLambda - startLambda));

        // java8中新的stream+forEach
        long startStream = System.currentTimeMillis();
        listUser.stream().forEach(User::toString);
        long endStream = System.currentTimeMillis();
        System.out.println("Stream:" + (endStream - startStream));

        // java8中新的parallelStream+forEach
        long startParallelStream = System.currentTimeMillis();
        listUser.parallelStream().forEach(User::toString);
        long endParallelStream = System.currentTimeMillis();
        System.out.println("ParallelStream:" + (endParallelStream - startParallelStream));
    }

    private List<User> getListUsers() {
        List<User> listUser = new ArrayList<User>();
        for (int i = 0; i < 10000000; i++) {
            listUser.add(new User("user" + i, i));
        }
        return listUser;
    }


}