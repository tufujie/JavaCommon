package com.jef.jdk8;

import com.jef.util.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jef
 * @date 2020/3/25
 */
public class StringJoinTest {

    @Test
    public void testJoin() {
        List<String> stringList = StringUtils.getTestStringList();
        // joining接收三个参数，第一个是分界符，第二个是前缀符，第三个是结束符。也可以不传入参数Collectors.joining()，这样就是直接拼接。
        System.out.println(stringList.stream().collect(Collectors.joining(",")));
    }

    @Test
    public void testJoinV2() {
        List<String> stringList = StringUtils.getTestStringList();
        System.out.println(String.join(",", stringList));
    }

    @Test
    public void testJoinV3() {
        List<String> stringList = StringUtils.getTestStringList();
        System.out.println(StringUtils.join(stringList.toArray(), ","));
    }
}