package com.jef.test;

import com.jef.common.utils.ContextUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.Locale;

/**
 * Spring国际化测试
 */
public class SpringInternationTest {

    @Test
    public void internationTest() {
        ApplicationContext applicationContext = ContextUtils.getContextFromBeansXML();
        // 使用
        String hello = applicationContext.getMessage("hello", new String[] {"Jef"}, Locale.getDefault(Locale.Category.FORMAT));
        String now = applicationContext.getMessage("now", new Object[]{new Date()}, Locale.getDefault(Locale.Category.FORMAT));
        System.out.println(hello);
        System.out.println(now);
    }
}
