package com.jef.collection;

import com.jef.constant.BasicEntity;
import com.jef.constant.BasicList;
import com.jef.entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 初始化List
 * 非初始化为ArrayList形式的需要转化为ArrayList，否则可能会在后续操作时产生UnsupportedOperationException
 * @author Jef
 * @date 2018/10/9 19:16
 */
public class ListTest {

    @Test
    public void testInitManyOne() {
        ArrayList<String> obj = new ArrayList<String>();
        obj.add("o1");
        obj.add("o2");
        System.out.println(obj);
    }

    /**
     * 比较常用且实用
     */
    @Test
    public void testInitManyTwo() {
        List<String> obj = Arrays.asList("o1", "o2");
        // 这种方法缺点是不能使用add方法
//        obj.add("o3");
        System.out.println(obj);
        // 建议使用这种方式
        ArrayList<String> objTwo = new ArrayList<String>(Arrays.asList("o1", "o2"));
        // 可以使用add方法
        objTwo.add("o3");
        System.out.println(objTwo);

    }

    @Test
    public void testInitManyThree() {
        ArrayList<String> obj = new ArrayList<String>();
        List list = Arrays.asList("o1","o2");
        obj.addAll(list);
        // 可以使用add方法
        obj.add("o3");
        System.out.println(obj);
    }

    @Test
    public void testInitManyFour() {
        // 把element复制count次填入ArrayList中
        ArrayList<String> obj = new ArrayList<String>(Collections.nCopies(1, "1"));
        // 可以使用add方法
        obj.add("o3");
        System.out.println(obj);
    }

    @Test
    public void testInitManyFive() {
        List list = new ArrayList() {
            private static final long serialVersionUID = 7712318012085963634L;
            {
                add("o1");
                add("o2");
            }
        };
        // 可以使用add方法，但是这种使用方法比较复杂，不推荐使用
        list.add("o3");
        System.out.println(list);
    }

    @Test
    public void testInitOne() {
        // 初始化多个的适用于初始化一个
        List<String> obj = Collections.singletonList("1");
        // 这种方法缺点是不能使用add方法
//        obj.add("2");
        System.out.println(obj);
    }

    @Test
    public void testInitByStream() {
        List<String> obj = Stream.of("1", "2").collect(Collectors.toList());
        // 可以使用add方法
        obj.add("3");
        System.out.println(obj);
    }
}