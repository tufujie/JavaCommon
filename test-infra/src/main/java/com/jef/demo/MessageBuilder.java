package com.jef.demo;

/**
 * @author tufujie
 * @date 2023/8/14
 */
public class MessageBuilder {
    public String getMessage(String name) {
        StringBuilder result = new StringBuilder();
        if (name == null || name.trim().length() == 0) {
            result.append("empty!");
        } else {
            result.append("Hello " + name);
        }
        return result.toString();
    }
}