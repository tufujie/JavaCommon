package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
    public static void main(String[] args) throws IOException {
        String str = FileGlobal.GLOBALSTRING;
        FileOutputStream fos = new FileOutputStream(FileGlobal.OUTFILENAME);
        fos.write(str.getBytes(FileGlobal.GBK));
        fos.close();
    }
}
