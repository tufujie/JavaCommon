package com.jef.api.req;

import com.jef.api.resp.ThirdSystemAApiOneResp;
import lombok.*;
import org.springframework.http.HttpMethod;

/**
 * @author tufujie
 * @date 2023/9/22
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdSystemAApiOneReq implements ThirdSystemBaseReq<ThirdSystemAApiOneResp> {

    private String requestId;

    @Override
    public String getReqPath() {
        return "/api/one";
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}