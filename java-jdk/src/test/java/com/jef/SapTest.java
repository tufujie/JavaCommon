package com.jef;

import com.jef.util.HttpClientUtil;
import com.jef.util.LogicUtils;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试SAP对接
 *
 * @author Jef
 * @date 2021/12/28
 */
public class SapTest {

    @Test
    public void testSap() {
        Map<String, Object> requestParams = new HashMap<>();
        Map<String, Object> baseInfoParams = new HashMap<>();
        Map<String, Object> messageParams = new HashMap<>();
        Map<String, Object> headerParams = new HashMap<>();
        Map<String, Object> itemParams = new HashMap<>();

        baseInfoParams.put("TRACE_ID", "1000001");
        baseInfoParams.put("SEND_TIME", "");
        baseInfoParams.put("SRC_SYS", "");
        baseInfoParams.put("TAR_SYS", "");
        baseInfoParams.put("SERVICE_NAME", "");
        baseInfoParams.put("RETRY_TIMES", "");

        headerParams.put("AUART", "ZS02");
        headerParams.put("ZZDDLB", "A");
        headerParams.put("ZZVBELN", "10000013");
        headerParams.put("VKORG", "3390");
        headerParams.put("VTWEG", "10");
        headerParams.put("SPART", "00");
        headerParams.put("KUNNR", "0002050206");
        headerParams.put("ZZPRCTR", "");
        headerParams.put("AUDAT", "20210923");
        headerParams.put("ERDAT", "20210923");
        headerParams.put("RTDAT", "20210923");
        headerParams.put("RTTIME", "152514");
        headerParams.put("FKDAT", "");

        itemParams.put("POSNR", "000010");
        itemParams.put("AUFNR", "");
        itemParams.put("MATNR", "P1C65Q60XX9H90XX00");
        itemParams.put("ZMENG", "1.00");
        itemParams.put("WERKS", "1066");
        itemParams.put("KNOWS", "CNY");
        itemParams.put("ZP02", "330.00");
        itemParams.put("ZP05", "");
        itemParams.put("ZP07", "");
        itemParams.put("ZC03", "");
        itemParams.put("MWST", "1");
        itemParams.put("BEDAT", "20210801");
        itemParams.put("ENDDAT", "20220901");

        headerParams.put("ITEMS", itemParams);
        messageParams.put("HEADER", headerParams);
        requestParams.put("BASEINFO", baseInfoParams);
        requestParams.put("MESSAGE", messageParams);

        String result = HttpClientUtil.httpClientWithBasicAuth("http://skygrppod01.skyworth.com:50000/RESTAdapter/BC_KDEE/ZIF059_KDEE_010", requestParams, "12", "123");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (LogicUtils.isNotNull(jsonObject)) {
            JSONObject messageJson = jsonObject.getJSONObject("MESSAGE");
            String status = messageJson.getString("STATUS");
            String message = messageJson.getString("ZMESSAGE");
            if ("S".equals(status)) {
            } else {
                System.out.println(message);
            }
        }
    }

}