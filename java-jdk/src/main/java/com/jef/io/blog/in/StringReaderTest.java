package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.IOException;
import java.io.StringReader;

public class StringReaderTest {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedReaderTest.read(FileGlobal.INFILENAME));
        int c;
        while ((c = in.read()) != -1) {
            System.out.print((char) c);
        }
    }
}
