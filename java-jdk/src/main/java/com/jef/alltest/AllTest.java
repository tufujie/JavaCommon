package com.jef.alltest;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * @author Jef
 * 全部测试
 */
public class AllTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        User user = (User) Class.forName("com.jef.entity.User").newInstance();
    }
}
