package com.jef.demo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tufujie
 * @date 2023/8/14
 */
public class MessageBuilderTest {
    @Test
    public void testGetMessage1() {
        MessageBuilder obj = new MessageBuilder();
        assertEquals("Hello test", obj.getMessage("test"));
        assertEquals("empty!", obj.getMessage(""));
        assertEquals("empty!", obj.getMessage(null));
    }
}