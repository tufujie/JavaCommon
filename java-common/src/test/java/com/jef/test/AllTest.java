package com.jef.test;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jef
 * @date 2020/2/16 0016
 */
public class AllTest {

    @Test
    public void testAutoUnboxing() {
        Integer a = new Integer(3);
        // 将3自动装箱成Integer类型
        Integer b = 3;
        int c = 3;
        // false 两个引用没有引用同一对象
        System.out.println(a == b);
        // true a自动拆箱成int类型再和c比较
        System.out.println(a == c);
    }

    @Test
    public void testTranslate () {
        String str = "test", tempStr = "";
        try {
            tempStr = new String(str.getBytes("ISO-8859-1"), "GBK");
            tempStr = tempStr.trim();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(tempStr);
    }

    @Test
    public void testMethodInvoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String str = "hello";
        Method m = str.getClass().getMethod("toUpperCase");
        // HELLO
        System.out.println(m.invoke(str));
    }

}