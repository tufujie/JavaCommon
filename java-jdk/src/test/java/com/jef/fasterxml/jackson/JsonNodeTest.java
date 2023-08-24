package com.jef.fasterxml.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.jef.util.JsonUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author tufujie
 * @date 2023/8/24
 */
public class JsonNodeTest {
    /**
     * 模拟页面传过来的数据，单个对象时
     */
    private static final String JSON_USER = "{\"name\": \"Jef\",\"phone\": \"18390220001\"}";
    ;
    /**
     * 模拟页面传过来的数据，多个对象集合时
     */
    private static final String JSON_ARRAY = "[{\"name\": \"Jef\",\"phone\": \"18390220001\"},{\"name\": \"Lisi\",\"phone\": \"18390220002\"}]";

    /**
     * 单个对象转换
     */
    @Test
    public void testJsonToEntity() throws Exception {
        JsonNode jsonNode = JsonUtil.json2pojo(JSON_USER, JsonNode.class);
        JsonNode nameNode = jsonNode.get("name");
        System.out.println(nameNode.textValue());
    }

    /**
     * 多个对象转换
     */
    @Test
    public void testJSONArrayOne() throws Exception {
        List<JsonNode> mapList = JsonUtil.json2list(JSON_ARRAY, JsonNode.class);
        for (JsonNode jsonNode : mapList) {
            JsonNode nameNode = jsonNode.get("name");
            System.out.println(nameNode.textValue());
        }
    }

}