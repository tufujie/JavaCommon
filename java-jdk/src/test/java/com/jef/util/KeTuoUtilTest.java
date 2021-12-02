package com.jef.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 * @date 2021/12/2
 */
public class KeTuoUtilTest {

    @Test
    public void testKeTuo() throws Exception {
        Map<String, Object> jsonObject = Maps.newTreeMap();
        jsonObject.put("appId", "10685");
        jsonObject.put("parkId", 1208);

        String now = String.valueOf(new Date().getTime());
//        Long now = System.currentTimeMillis();
        jsonObject.put("reqId", REIDIdentifier.randomEOID());
        jsonObject.put("serviceCode", "addCarCardNo");
        jsonObject.put("ts", now);
        System.out.println(now);
        jsonObject.put("userId", 1);
        jsonObject.put("userName", "admin");

        // 车位
        JSONArray carLotList = new JSONArray();
        Map<String, Object> carLot = Maps.newTreeMap();
        carLot.put("areaId", new String[]{"2"});
        carLot.put("areaName", "地面");
        carLot.put("carType", 2);
        carLot.put("lotCount", 1);
        carLot.put("lotName", "月1131456");
        carLot.put("sequence", 1);
        carLotList.add(carLot);

        // 车主
        Map<String, Object> cardInfo = Maps.newTreeMap();
        cardInfo.put("cardName", "月1131456");
        cardInfo.put("useName", "ttt131456");
        cardInfo.put("tel", "15592184582");

        // 车牌
        JSONArray plateNoInfoList = new JSONArray();
        Map<String, Object> plateNoInfo = Maps.newTreeMap();
        plateNoInfo.put("plateNo", "A12131456");
        plateNoInfoList.add(plateNoInfo);

        jsonObject.put("carLotList", JSONObject.toJSONString(carLotList));
        jsonObject.put("cardInfo", JSONObject.toJSONString(cardInfo));
        jsonObject.put("plateNoInfo", JSONObject.toJSONString(plateNoInfoList));

        String appSecret = "test123";
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.putAll(jsonObject);
        String sign = KeTuoSignUtils.paramsSign(jsonObject1, appSecret);
        System.out.println(sign);
        jsonObject.put("key", sign);
        String url = "http://kp-open.keytop.cn/unite-api/api/wec/AddCarCardNo";
        Map<String, String> headParams = Maps.newHashMap();
        headParams.put("version", "1.0.0");
        headParams.put("Content-Type", "application/json; charset=utf-8");
        String jsonParam = JSONObject.toJSONString(jsonObject);
        System.out.println("请求参数");
        System.out.println(jsonParam);
        String response = HttpClientUtil.sendPostRequest(url, jsonParam, headParams, "UTF-8", "UTF-8");
        System.out.println(response);
    }

}