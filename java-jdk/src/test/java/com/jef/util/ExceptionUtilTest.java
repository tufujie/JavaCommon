package com.jef.util;

import com.jef.constant.BasicConstant;
import com.jef.entity.User;

import org.junit.jupiter.api.Test;

/**
 * 测试异常工具类
 *
 * @author Administrator
 * @date 2021/12/28
 */
public class ExceptionUtilTest {

    @Test
    public void testGetExceptionInfo() {
        Integer a = 1, b = 0;
        try {
            int c = a / b;
        } catch (Exception e) {
            ExceptionUtil.getExceptionStackTraceMessage("做除法异常", e);
        }
        PrintUtil.printSplitLine();

        User user = new User();
        try {
            if (user.getName().equals(BasicConstant.USER_NAME)) {
                PrintUtil.printf("userName=%s", user.getName());
            }
        } catch (Exception e) {
            ExceptionUtil.getExceptionStackTraceMessage("作比较异常", e);
        }
    }

}