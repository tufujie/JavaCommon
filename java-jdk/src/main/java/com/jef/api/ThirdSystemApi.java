package com.jef.api;

import com.jef.api.req.ThirdSystemBaseReq;

public interface ThirdSystemApi {

    <T> T invoke(ThirdSystemBaseReq<T> req);

}
