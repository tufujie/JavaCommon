package com.jef.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2019/9/11
 */
public class HttpClientUtilTest {

    /**
     * 测试get请求
     * @author Jef
     * @date 2019/9/11
     */
    @Test
    public void testGetRequest() {
        List<Map<String, String>> listMap = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        map.put("cookie", "******");
        listMap.add(map);
        System.out.println(JSONObject.toJSONString(listMap));
        String responseStr = HttpClientUtil.sendGetRequest("https://api.***.com/users/getCurrentUserInfo", map, HttpClientUtil.CHARSET_UTF);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        System.out.println("data=" + jsonObject.get("data"));
    }

    /**
     * 测试get请求
     * @author Jef
     * @date 2019/9/11
     */
    @Test
    public void testPostRequest() throws Exception {
        List<Map<String, String>> listMap = Lists.newArrayList();
        Map<String, String> map = Maps.newHashMap();
        map.put("access_token", "test");
        map.put("cookie", "******");
        listMap.add(map);
        String sendData = JSONObject.toJSONString(listMap);
        System.out.println(sendData);
        String responseStr = HttpClientUtil.sendPostRequest("http://api.***.com/api/operationCenter/pushOperationCenterData", map, HttpClientUtil.CHARSET_UTF, HttpClientUtil.CHARSET_UTF);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        System.out.println("data=" + jsonObject.get("data"));
    }

    /**
     * 测试get请求
     * @author Jef
     * @date 2019/9/11
     */
    @Test
    public void testPostRequestV2() throws Exception {
        List<Map<String, String>> listMap = Lists.newArrayList();
        Map<String, String> param = Maps.newHashMap();
        param.put("id", "bf7fc3f32d4243a3af4b70c99bc63492");
        param.put("status", "3");
        Map<String, String> headParams = Maps.newHashMap();
        headParams.put("cookie", "dddjQ4NmMyMTRkZjE1");
        listMap.add(headParams);
        String sendData = JSONObject.toJSONString(listMap);
        System.out.println(sendData);
        String responseStr = new HttpClientUtil().sendHttpPost("https://admin.***.com/test/testAdd", headParams, param);
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        System.out.println("data=" + jsonObject.get("data"));
        System.out.println(responseStr);
    }
}