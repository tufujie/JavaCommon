package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamReaderTest {
    public static void main(String[] args) throws IOException {
        File file = new File(FileGlobal.INFILENAME);
        InputStreamReader read = new InputStreamReader(new FileInputStream(file));
        char[] b = new char[FileGlobal.BSIZE];
        int len = read.read(b);
        read.close();
        System.out.println(new String(b,0,len));
    }
}
