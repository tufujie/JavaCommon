package com.jef.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 逻辑判断测试
 * @author Jef
 * @date 2019/12/9
 */
public class LogicUtilTest {

    @Test
    public void testChahangeFlag() {
        BigDecimal a = new BigDecimal("1.0000"), b = new BigDecimal("1"), c = null;
        System.out.println("价格1" + LogicUtils.isChange(a, b));
        // 变化
        System.out.println("价格2" + LogicUtils.isChange(b, c));
        // 变化
        System.out.println("价格3" + LogicUtils.isChange(c, b));
        System.out.println("价格4" + LogicUtils.isChange(c, c));
        int num1 = 1, num2 = 2;
        // 变化
        System.out.println("数字1" + LogicUtils.isChange(num1, num2));
        String d = "test", e = "test",  f = null, g = "test1";
        System.out.println("文字1" + LogicUtils.isChange(d, e));
        // 变化
        System.out.println("文字2" + LogicUtils.isChange(e, g));
        // 变化
        System.out.println("文字3" + LogicUtils.isChange(f, g));
        System.out.println("文字4" + LogicUtils.isChange(f, f));
        Date date1 = null, date2 = null, date3 = new Date();
        System.out.println("日期1" + LogicUtils.isChange(date1, date2));
        // 变化
        System.out.println("日期2" + LogicUtils.isChange(date2, date3));
        // 变化
        System.out.println("日期3" + LogicUtils.isChange(date3, date2));
        System.out.println("日期4" + LogicUtils.isChange(date3, date3));
    }
}