package com.jef.designpattern.structure.decorator.one;

import com.jef.constant.BasicConstant;

import java.util.Map;

/**
 * @author Jef
 * @date 2021/12/8
 */
public class LoginSsoDecorator extends SsoDecorator {

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }


    @Override
    public boolean preHandle(Map<String, String> requestData) {
        String userId = requestData.get("userId");
        String method = requestData.get("method");
        String successStr = requestData.get("success");
        System.out.printf("模拟单点登录方法访问拦截校验:userID=%s method=%s success=%s", userId, method, successStr);
        System.out.println();
        boolean success = super.preHandle(requestData);
        if (!success) {
            return false;
        }
        // 模拟方法校验
        return BasicConstant.LOGIN_OBJECT_KEY.equals(method);
    }
}