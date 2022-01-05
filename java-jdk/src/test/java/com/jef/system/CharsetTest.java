package com.jef.system;

import org.junit.jupiter.api.Test;

public class CharsetTest {

    @Test
    public void testGetFileEncoding() {
        System.out.println("系统默认编码为：" + System.getProperty("file.encoding"));
    }
}
