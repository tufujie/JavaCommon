package com.jef.api;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jef.api.req.ThirdSystemBaseReq;
import com.jef.api.resp.ThirdSystemBaseResp;
import com.jef.constant.BasicConstant;
import com.jef.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author tufujie
 * @date 2023/9/22
 */
public class ThirdSystemApiAImpl implements ThirdSystemApi {

    private static volatile ObjectMapper MAPPER;

    @Override
    public <T> T invoke(ThirdSystemBaseReq<T> req) {
        ThirdSystemBaseResp<T> resp = callThirdSystemApi(req);
        return resp.getData();
    }

    public <T> ThirdSystemBaseResp<T> callThirdSystemApi(ThirdSystemBaseReq<T> req) {
        Class<T> responseType = (Class<T>) GenericTypeHelper.getTypeArgument(req.getClass(), ThirdSystemBaseReq.class);
        return callThirdSystemApi(req, responseType);
    }

    public <T> ThirdSystemBaseResp<T> callThirdSystemApi(ThirdSystemBaseReq req, Class<T> respType) {
        String reqPath = req.getReqPath();
        HttpMethod method = req.getHttpMethod();
        String url;
        HttpEntity<?> entity;
        HttpHeaders headers = createHeaders();
        Map<String, Object> queryParams = Collections.emptyMap();
        if (method == HttpMethod.GET) {
            queryParams = toMap(req);
            url = getUrl(reqPath, queryParams);
            entity = new HttpEntity<>(headers);
        } else {
            url = getUrl(reqPath);
            headers.setContentType(MediaType.APPLICATION_JSON);
            String bodyString = toString(req);
            entity = new HttpEntity<>(bodyString, headers);
        }
        // 真实调用
        /*ThirdSystemBaseResp rawResp =
                new RestTemplate().exchange(url, method, entity, ThirdSystemBaseResp.class, queryParams).getBody();*/
        ThirdSystemBaseResp rawResp = exchange(url, method, entity, ThirdSystemBaseResp.class, queryParams);
        T data = getMapper().convertValue(rawResp.getData(), respType);
        rawResp.setData(data);
        return rawResp;
    }

    /**
     * 模拟真实响应
     *
     * @param url
     * @param method
     * @param requestEntity
     * @param responseType
     * @param uriVariables
     * @return
     */
    public <T> ThirdSystemBaseResp<T> exchange(String url, HttpMethod method, HttpEntity<?> requestEntity,
                                               Class<T> responseType, Map<java.lang.String, ?> uriVariables) {
        System.out.println("请求接口：" + url + " 请求参数：" + requestEntity.toString());
        ThirdSystemBaseResp result = new ThirdSystemBaseResp<T>();
        result.setCode("0000");
        result.setMsg("操作成功");
        result.setSuccess(true);
        if (url.contains("one")) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", BasicConstant.USER_NAME);
            jsonObject.put("phone", BasicConstant.USER_PHONE);
            result.setData(jsonObject);
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("age", 30);
            jsonObject.put("manageName", BasicConstant.USER_NAME);
            result.setData(jsonObject);
        }
        System.out.println("响应" + JSONObject.toJSONString(result));
        return result;
    }


    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "TEST");
        return headers;
    }


    private String getUrl(String path, Map<String, Object> uriVariables) {
        StringBuilder sb = new StringBuilder(getUrl(path));
        if (!CollectionUtils.isEmpty(uriVariables)) {
            sb.append("?");
            uriVariables.forEach((k, v) -> sb.append(k).append('=').append(v).append('&'));
        }
        return sb.toString();
    }

    private String getUrl(String path) {
        return "http://127.0.0.1" + path;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(Object src) {
        return getMapper().convertValue(src, Map.class);
    }

    public static ObjectMapper getMapper() {
        if (MAPPER == null) {
            synchronized (JsonUtil.class) {
                if (MAPPER == null) {
                    MAPPER = createMapper();
                }
            }
        }
        return MAPPER;
    }

    public static String toString(Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (final Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 解析JSON时， 无视POJO中没有定义的key-value
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 序列化时，没有值的属性可以序列化成功
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // 允许双斜杠注释
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 值为null的属性不参与序列化
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 时区设置为格林威治标准时间加8小时（东八区）
        mapper.setTimeZone(TimeZone.getTimeZone("UTC"));
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置枚举不匹配时设置为null
        mapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
     /*   // 设置序列化时剪切空格
        StringTrimModule module = new StringTrimModule();
        mapper.registerModule(module);*/
        // 注册Java8时间序列化模块
/*        JavaTimeModule javaTimeModule = new JavaTimeModule();
//        javaTimeModule.addSerializer(ZonedDateTime.class, new ZonedDateTimeSerializer(StandardDateCode.ISO8601.getFormatter()));
        mapper.registerModule(javaTimeModule);*/
        // 设置序列化时忽略未知过滤器
        mapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
        return mapper;
    }
}