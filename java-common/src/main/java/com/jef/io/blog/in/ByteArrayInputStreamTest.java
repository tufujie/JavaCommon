package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamTest {
    public static void main(String[] args) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(BufferedReaderTest.read(FileGlobal.INFILENAME).getBytes());
        int temp;
        StringBuilder sb = new StringBuilder();
        while((temp = bais.read()) != -1) {
            char ch = (char) temp;
            sb.append(ch);
        }
        System.out.println(sb);
    }
}
