package com.jef.reflect;

import com.jef.business.BusinessDemo;
import com.jef.constant.BasicConstant;
import com.jef.constant.BasicEntity;
import com.jef.entity.TestAll;
import com.jef.entity.User;
import com.jef.util.PrintUtil;
import com.jef.util.ReflectionUtil;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

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
     * 测试获取、设置属性值
     *
     * @author Jef
     * @date 2021/9/27
     */
    @Test
    public void testGetSetFieldInfo() throws Exception {
        TestAll testAll = new TestAll();
        testAll.setTestName(BasicConstant.USER_NAME);
        ReflectionUtil.printFieldAndValue(testAll);
        PrintUtil.printSplitLine();

        ReflectionUtil.printFieldAndValue(testAll, "testName");
        ReflectionUtil.setFieldValue(testAll, "testName", BasicConstant.USER_NAME);
        ReflectionUtil.printFieldAndValue(testAll, "testName");
        PrintUtil.printSplitLine();

        ReflectionUtil.getObjectValue(testAll);

        ReflectionUtil.invokeSetter(testAll, "testName", "invokeSetterName");
        System.out.println("invokeGetter=" + ReflectionUtil.invokeGetter(testAll, "testName"));
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
     * 通过类、属性、值反射获取带有属性值的对象测试
     *
     * @author Jef
     * @date 2021/9/27
     */
    @Test
    public void testGetObject() throws InstantiationException, IllegalAccessException {
        Class clazz = ReflectionUtil.loadClass("com.jef.entity.TestAll");
        TestAll testAll = (TestAll) clazz.newInstance();
        System.out.println(testAll.getTestName());
    }

    /**
     * 调用方法
     */
    @Test
    public void testReflectPropertyTypeAndValue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        User user = BasicEntity.getUser();
        Class clazz = user.getClass();
        Method method = clazz.getDeclaredMethod("get" + "Name");
        Object nameValue = method.invoke(user);
        System.out.println("nameValue=" + nameValue);
    }

    /**
     * 利用反射执行方法测试
     */
    @Test
    public void testReflectInvokeMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BusinessDemo businessDemo = new BusinessDemo();
        Class clazz = businessDemo.getClass();
        // 方法参数是Map
        Method methodMap = clazz.getDeclaredMethod("handlerBusiness", Map.class);
        Map<String, Object> map = new HashMap<>(6);
        map.put("id", BasicConstant.FIRST_ORDER_ID);
        methodMap.invoke(businessDemo, map);
        // 方法参数是各种类型
        Method methodManyKindType = clazz.getDeclaredMethod("taskHasReturn", int.class, String.class);
        methodManyKindType.invoke(businessDemo, 10, "利用反射执行方法测试");
    }


}