package com.jef.algorithm;

import com.jef.string.StringCaseInsensitiveCompare;
import com.jef.util.IntegerUtil;
import com.jef.util.StringUtils;
import org.junit.Test;

/**
 * @author Jef
 * @date 2020/4/5
 */
public class AnyTypeCompareTest {

    @Test
    public void testFindIntegerMax() {
        System.out.println(AnyTypeCompare.findMax(IntegerUtil.getTestIntegerList(), Integer::compareTo));
    }

    @Test
    public void testFindStringMax() {
        System.out.println(AnyTypeCompare.findMax(StringUtils.getTestStringList(), String::compareTo));
    }

    @Test
    public void testFindStringCaseInsensitiveMax() {
        System.out.println(AnyTypeCompare.findMax(StringUtils.getTestStringList(), new StringCaseInsensitiveCompare()));
    }
}