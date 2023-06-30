package com.jef.designpattern.structure.decorator.one;

import java.util.Map;

/**
 * 公共拦截
 *
 * @author Jef
 * @date 2021/12/8
 */
public interface HandlerInterceptor {

    /**
     * 业务处理
     *
     * @param requestData 请求数据
     * @return boolean
     * @author Jef
     * @date 2021/12/8
     */
    boolean preHandle(Map<String, String> requestData);
}
