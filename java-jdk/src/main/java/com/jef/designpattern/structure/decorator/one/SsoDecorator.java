package com.jef.designpattern.structure.decorator.one;

import java.util.Map;

/**
 * @author Jef
 * @date 2021/12/8
 */
public class SsoDecorator implements HandlerInterceptor {
    private HandlerInterceptor handlerInterceptor;

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandle(Map<String, String> requestData) {
        return handlerInterceptor.preHandle(requestData);
    }
}