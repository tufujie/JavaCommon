package com.jef.util;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * IdChangeUtil测试类
 * @author Jef
 * @date 2019/5/31
 */
public class IdChangeUtilTest {
    @Test
    public void testSolveChangeID() {
        Set<String> oldSet = com.google.common.collect.Sets.newHashSet();
        oldSet.add("1");
        oldSet.add("2");
        oldSet.add("3");
        Set<String> nowSet = Sets.newHashSet();
        nowSet.add("3");
        nowSet.add("4");
        nowSet.add("5");
        Map<String, Set<String>> resultMap = IdChangeUtil.solveChangeID(nowSet, oldSet);
        Set<String> addSet = resultMap.get("addSet");
        Set<String> removeSet = resultMap.get("removeSet");
        System.out.println(addSet);
        System.out.println(removeSet);
    }
}