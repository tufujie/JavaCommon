package com.jef.reflect;

import com.jef.constant.BasicConstant;
import com.jef.entity.TestAll;
import com.jef.util.ReflectionUtil;

import org.junit.Test;

/**
 * 反射测试
 * @author Jef
 * @date 2020/3/31
 */
public class ReflectTest {

    @Test
    public void testLoadClass() throws InstantiationException, IllegalAccessException {
        Class clazz = ReflectionUtil.loadClass("com.jef.entity.TestAll");
        TestAll testAll = (TestAll) clazz.newInstance();
        testAll.setTestName(BasicConstant.USER_NAME);
    }

    /**
     * 测试获取属性值
     * @author Jef
     * @date 2021/9/27
     */
    @Test
    public void testGetFieldAndValue() {
        TestAll testAll = new TestAll();
        testAll.setTestName(BasicConstant.USER_NAME);
        ReflectionUtil.printFieldAndValue(testAll);
        System.out.println("--------");
        ReflectionUtil.printFieldAndValue(testAll, "testName");
    }

    /**
     * 测试获取方法
     * @author Jef
     * @date 2021/9/27
     */
    @Test
    public void testPrintMethod() {
        TestAll testAll = new TestAll();
        testAll.setTestName(BasicConstant.USER_NAME);
        ReflectionUtil.printMethods(testAll);
    }

    /**
     * 测试通过类、属性、值反射获取带有属性值的对象
     * @author Jef
     * @date 2021/9/27
     */
    @Test
    public void testGetObject() throws InstantiationException, IllegalAccessException {
        Class clazz = ReflectionUtil.loadClass("com.jef.entity.TestAll");
        TestAll testAll = (TestAll) clazz.newInstance();
        ReflectionUtil.setFieldValue(testAll, "testName", BasicConstant.USER_NAME);
        ReflectionUtil.printFieldAndValue(testAll);
    }
}