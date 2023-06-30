package com.jef.designpattern.structure.decorator.one;

import java.util.Map;

/**
 * 单点登录拦截
 *
 * @author Jef
 * @date 2021/12/8
 */
public class SsoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(Map<String, String> requestData) {
        // 模拟获取cookie
        String success = requestData.get("success");
        // 模拟校验
        return "success".equals(success);
    }
}