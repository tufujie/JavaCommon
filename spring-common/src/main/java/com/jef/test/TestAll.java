package com.jef.test;

import com.jef.util.StringUtils;
import org.springframework.jms.connection.CachingConnectionFactory;

/**
 * @author Jef
 * @date 2020/4/4
 */
public class TestAll {
    public static void main(String[] args) {
        String a = "%257B%2522projectID%2522%253A%2522319c23c4c60e4def9959c776c32ca7f9%2522%252C%2522productTypeID%2522%253A%252218e1f734aec44ab6ba8eb6cd2d6f2890%2522%252C%2522propertyStatus%2522%253Anull%252C%2522searchDate%2522%253Anull%252C%2522excludeSelf%2522%253A%2522%2522%257D";
        System.out.println(StringUtils.decodeURLCharset(a));
    }
}