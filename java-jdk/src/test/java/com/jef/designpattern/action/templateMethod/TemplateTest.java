package com.jef.designpattern.action.templateMethod;

import com.jef.designpattern.action.templateMethod.impl.JDNetMall;
import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 测试模板模式
 *
 * @author Jef
 * @date 2021/12/12
 */
public class TemplateTest {

    @Test
    public void testTemplate() {
        NetMall netMall = new JDNetMall("1000001", "*******");
        String base64 = netMall.generateGoodsPoster("https://item.jd.com/100008348542.html");
        PrintUtil.printf("测试结果：%s", base64);
    }

}