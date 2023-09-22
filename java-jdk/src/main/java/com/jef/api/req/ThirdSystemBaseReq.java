package com.jef.api.req;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpMethod;

/**
 * @param <Resp> 响应类型
 */
public interface ThirdSystemBaseReq<Resp> {

    /**
     * 获取请求路径
     *
     * @return 请求路径
     */
    @JsonIgnore
    String getReqPath();

    /**
     * 获取请求方法
     *
     * @return 请求方法
     */
    @JsonIgnore
    default HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
