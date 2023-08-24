package com.jef.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jef.constant.BasicConstant;
import com.jef.entity.User;
import com.jef.util.DateTimeUtil;
import com.jef.util.PrintUtil;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * FastJson功能测试类
 * @author Jef
 * @create 2018/7/20 12:07
 */
public class FastJsonTest {
    /**
     * 模拟页面传过来的数据，单个对象时
     */
    private static final String JSON_USER = "{'name': 'Jef','phone': '18390220001', 'age': 20, 'isMain': 0}";
    /**
     * 模拟页面传过来的数据，多个对象集合时
     */
    private static final String JSON_ARRAY = "[{'name':'Jef','phone': '18390220001', 'age': 20},{'name':'Ran','phone': '18390220002', 'age': 19}]";
    /**
     * 模拟页面传过来的数据，多个对象时
     */
    private static final String JSON_ARRAY_USER_WITHTIME = "{user : [{'name' : 'Jef', 'phone': '18390220001', 'age': 20, 'createTime' : '2018-08-19', 'updateTime' : '2018-08-20', 'auditTime' : '2018-08-19 12:00:59'}, {'name':'Ran','phone': '18390220002', 'age': 19, 'createTime' : '2018-08-20', 'updateTime' : '2018-08-20', 'auditTime' : '2018-08-19 12:00:59'}]}";

    @Test
    public void allTest() {
        // 字符串转换成json数据
        String userOneStr = JSON_USER;
        // Oject转JSON
        String str_json1 = JSON.toJSONString(userOneStr, true);
        String str_json2 = JSON.toJSONString(userOneStr, false);
        System.out.println("格式化数据" + str_json1);
        System.out.println("未格式化数据" + str_json2);

        // 输出jsonObject中的数据
        JSONObject object = JSON.parseObject(userOneStr);
        System.out.println("获取json数据中的数据" + object.get("name") + " " + object.get("phone") + " " + object.get("age"));
        // 删除json中的数据
        String propertyName = "name";
        Set set = object.keySet();
        set.remove(propertyName);
        // object.remove(propertyName);
        System.out.println("删除数据之后的json格式" + object.toString());
        // 添加属性value值
        String addPropertyName = "sex";
        String addPropertyVlaue = "man";
        object.put(addPropertyName, addPropertyVlaue);
        System.out.println("输出新增后的json数据" + object.toString());
        // 修改属性的值等同于覆盖值
        String updatePropertyName = "sex";
        String updatePropertyVlaue = "woman";
        Set set2 = object.keySet();
        if (set2.contains(updatePropertyName)) {
            // object.put(updatePropertyName, JSON.toJSONString(updatePropertyVlaue));
            object.put(updatePropertyName, updatePropertyVlaue);
        }
        System.out.println("输出修改属性值的json数据" + object.toString());
        // 判断json是否存在属性
        System.out.println("是否存在属性值id=" + object.keySet().contains("id"));
        // json字符串转map方式1，parse方法也可
        Map mapNew = JSON.parseObject(userOneStr);
        System.out.println(mapNew);
        // json字符串转map方式2，parse方法也可
        Map mapNewTwo = JSONObject.parseObject(userOneStr);
        System.out.println(mapNewTwo);
        // 转换日期，这个还是比较重要
        Object date = new Date();
        String date_json = JSON.toJSONStringWithDateFormat(date, DateTimeUtil.DATE_FORMAT_SSS);
        System.out.println("日期处理" + date_json);
    }

    /**
     * 单个对象转换
     */
    @Test
    public void testJsonToEntity() {
        // json转化简单的实体类
        User user = JSONObject.parseObject(JSON_USER, User.class);
        System.out.println(user);
        User userTwo = JSON.parseObject(JSON_USER, User.class);
        System.out.println(userTwo);
    }

    /**
     * 单个对象转换
     */
    @Test
    public void testJSONObjectToEntity() {
        JSONObject object = JSON.parseObject(JSON_USER);
        // JSONObject 转 Entity
        User user = object.toJavaObject(User.class);
        System.out.println(user);
    }

    /**
     * 遍历JSONObject
     */
    @Test
    public void testGetJSONObjectEach() {
        JSONObject object = JSON.parseObject(JSON_USER);
        for (Map.Entry<String, Object> entry : object.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * 解析array，JSONArray
     * JSON中只有array时
     */
    @Test
    public void testJSONArrayOne() {
        List<User> userList = JSONArray.parseArray(JSON_ARRAY, User.class);
        System.out.println("输出集合大小" + userList.size());
        for (User u : userList) {
            System.out.println(u);
        }
    }

    /**
     * 解析array，JSONArray，Two和Three的结合，实用，含有字符串时间，也会自动转换
     * JSON中不止包含array时
     */
    @Test
    public void testJSONArrayTwo() {
        JSONObject jsonObject = JSONObject.parseObject(JSON_ARRAY_USER_WITHTIME);
        String jsonArrayUser = jsonObject.getString("user");
        // 这种需要保证传过来的key的名称和实体中的属性一致，可以不用自己解析，建议使用
        List<User> userList = JSONArray.parseArray(jsonArrayUser, User.class);
        System.out.println("输出集合大小" + userList.size());
        for (User u : userList) {
            System.out.println(u);
        }
        List<User> userList2 = JSON.parseArray(jsonArrayUser, User.class);
        System.out.println("输出集合大小" + userList2.size());
        for (User u : userList2) {
            System.out.println(u);
        }
        // for循环解析，不建议使用
        JSONArray jsonArray = jsonObject.getJSONArray("user");
        printJSONArray(jsonArray);

    }


    /**
     * 输出JSONArray中的数据
     * @param jsonArray
     */
    private static void printJSONArray(JSONArray jsonArray) {
        for (Object aJsonArray : jsonArray) {
            JSONObject obj = (JSONObject) aJsonArray;
            System.out.println("name=" + obj.get("name"));
        }
        PrintUtil.printSplitLine();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            System.out.println("name=" + obj.get("name"));
        }
    }

    /**
     * mapt转json的字符串
     */
    @Test
    public void testMapToJsonStr() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Jef");
        map.put("phone", "18390220001");
        map.put("age", 20);
        String map_json = JSON.toJSONString(map);
        System.out.println("map转换成json数据" + map_json);
    }

    /**
     * map转json的字符串，然后从json字符串中读取
     */
    @Test
    public void testMapToJsonStrAndRead() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "Jef");
        map1.put("phone", "18390220001");
        map1.put("age", 20);
        String map_json = JSON.toJSONString(map1);
        System.out.println("map转换成json数据" + map_json);
        JSONObject jsonObject = JSON.parseObject(map_json);
        System.out.println("获取map集合中的数据" + jsonObject.get("name") + " " + jsonObject.get("phone") + " " +
                jsonObject.get("age"));
    }

    /**
     * mapt转json的字符串
     */
    @Test
    public void testObjectToJsonStrToObject() {
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        String userStr = JSON.toJSONString(user);
        System.out.println("Object转换成String数据" + userStr);
        User userObj = JSON.parseObject(userStr, User.class);
        System.out.println("String转换成Object数据" + userObj);
    }

}
