package com.jef.api.req;

import com.jef.api.resp.ThirdSystemAApiTwoResp;
import lombok.*;

/**
 * @author tufujie
 * @date 2023/9/22
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdSystemAApiTwoReq implements ThirdSystemBaseReq<ThirdSystemAApiTwoResp> {

    private String requestId;

    @Override
    public String getReqPath() {
        return "/api/two";
    }
    
}