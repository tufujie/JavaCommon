package com.jef.reflect;

import com.jef.constant.BasicConstant;
import com.jef.entity.TestAll;
import com.jef.util.ReflectionUtils;
import org.junit.Test;

/**
 * 反射测试
 * @author Jef
 * @date 2020/3/31
 */
public class ReflectTest {

    @Test
    public void testGetFieldAndValue() {
        TestAll testAll = new TestAll();
        testAll.setTestName(BasicConstant.USER_NAME);
        ReflectionUtils.printFieldAndValue(testAll);
    }

    @Test
    public void testPrintMethod() {
        TestAll testAll = new TestAll();
        testAll.setTestName(BasicConstant.USER_NAME);
        ReflectionUtils.printMethods(testAll);
    }
}