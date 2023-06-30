package com.jef.designpattern.structure.decorator;

import com.jef.constant.BasicConstant;
import com.jef.designpattern.structure.decorator.one.LoginSsoDecorator;
import com.jef.designpattern.structure.decorator.one.SsoInterceptor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jef
 * @date 2021/12/8
 */
public class DecoratorTest {

    @Test
    public void testDecorator() {
        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        Map<String, String> requestData = new HashMap<String, String>();
        requestData.put("userId", BasicConstant.USER_NAME);
        requestData.put("method", BasicConstant.LOGIN_OBJECT_KEY);
        requestData.put("success", BasicConstant.SUCCESS);
        boolean success = loginSsoDecorator.preHandle(requestData);
        Assertions.assertTrue(success, BasicConstant.USER_NAME + "登录失败");
        requestData.put("success", BasicConstant.ERROR);
        success = loginSsoDecorator.preHandle(requestData);
        Assertions.assertFalse(success, BasicConstant.USER_NAME + "登录成功");
    }

}