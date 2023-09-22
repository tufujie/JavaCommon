package com.jef.api;

import com.alibaba.fastjson.JSONObject;
import com.jef.api.req.ThirdSystemAApiOneReq;
import com.jef.api.req.ThirdSystemAApiTwoReq;
import com.jef.api.resp.ThirdSystemAApiOneResp;
import com.jef.api.resp.ThirdSystemAApiTwoResp;
import com.jef.util.PrintUtil;

/**
 * @author tufujie
 * @date 2023/9/22
 */
public class ThirdSystemReqRespClient {

    public static void main(String[] args) {
        System.out.println("开始接口1请求");
        ThirdSystemAApiOneReq thirdSystemAApiOneReq = ThirdSystemAApiOneReq.builder().requestId("test123").build();
        ThirdSystemAApiOneResp respOne = new ThirdSystemApiAImpl().invoke(thirdSystemAApiOneReq);
        System.out.println(JSONObject.toJSONString(respOne));


        PrintUtil.printSplitLine();

        System.out.println("开始接口2请求");
        ThirdSystemAApiTwoReq thirdSystemAApiTwoReq = ThirdSystemAApiTwoReq.builder().requestId("test123").build();
        ThirdSystemAApiTwoResp respTwo = new ThirdSystemApiAImpl().invoke(thirdSystemAApiTwoReq);
        System.out.println(JSONObject.toJSONString(respTwo));
    }

}