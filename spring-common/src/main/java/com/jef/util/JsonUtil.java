package com.jef.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * jsonUtil工具类
 * @Author: liyh
 * @Date: 2020/9/17 17:12
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static ObjectMapper mapper = new ObjectMapper();

    private JsonUtil() {
        //工具类无需对象实例化
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public static String toJson(Object object, boolean isFormat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if (isFormat) {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } else {
            return mapper.writeValueAsString(object);
        }
    }

    public static String toJson(Object object) throws JsonProcessingException {
        return toJson(object, false);
    }

    public static <T> T toObject(String content, Class<T> c)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, c);
    }

    public static <T> List<T> toListObject(String content, Class<T> c) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType t = getCollectionType(ArrayList.class, c);
        return (List<T>) mapper.readValue(content, t);
    }

    public static <T> Map<String, T> toMapObject(String content, Class<T> c) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JavaType t = getCollectionType(HashMap.class, String.class, c);
        return (Map<String, T>) mapper.readValue(content, t);
    }


    /**
     * 对象转Json格式字符串
     * @param obj 对象
     * @return Json格式字符串
     */
    public static <T> String obj2String(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Parse Object to String error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 对象转Json格式字符串(格式化的Json字符串)
     * @param obj 对象
     * @return 美化的Json格式字符串
     */
    public static <T> String obj2StringPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.warn("Parse Object to String error : {}", e.getMessage());
            return null;
        }
    }

    /**
     * 字符串转换为自定义对象
     * @param str 要转换的字符串
     * @param clazz 自定义对象的class对象
     * @return 自定义对象
     */
    public static <T> T jsonToObj(String str, Class<T> clazz){
        if(StringUtils.isEmpty(str) || clazz == null){
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : mapper.readValue(str, clazz);
        } catch (Exception e) {
            LOGGER.warn("Parse String to Object error : {}", e.getMessage());
            return null;
        }
    }


    /**
     * 集合对象与Json字符串之间的转换
     * @param str 要转换的字符串
     * @param typeReference 集合类型如List<Object>
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String str, TypeReference<T> typeReference) {
        if (StringUtils.isEmpty(str) || typeReference == null) {
            return null;
        }
        try {
            return (T) (typeReference.getType().equals(String.class) ? str : mapper.readValue(str, typeReference));
        } catch (IOException e) {
            LOGGER.warn("Parse String to Object error", e);
            return null;
        }
    }

    /**
     * 集合对象与Json字符串之间的转换
     * @param str 要转换的字符串
     * @param collectionClazz 集合类型
     * @param elementClazzes 自定义对象的class对象
     * @param <T>
     * @return
     */
    public static <T> T string2Obj(String str, Class<?> collectionClazz, Class<?>... elementClazzes) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClazz, elementClazzes);
        try {
            return mapper.readValue(str, javaType);
        } catch (IOException e) {
            LOGGER.warn("Parse String to Object error : {}" + e.getMessage());
            return null;
        }
    }
}

