package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {
    public static void main(String[] args) throws IOException {
        String str = FileGlobal.GLOBALSTRING;
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(FileGlobal.OUTFILENAME));
        bos.write(str.getBytes(FileGlobal.GBK));
        bos.close();
    }
}
