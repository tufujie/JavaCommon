package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class BufferedInputStreamTest {
    public static void main(String[] args) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(FileGlobal.INFILENAME));
        int bestAvailable = bis.available();
        if (bestAvailable > 0) {
            byte[] buf = new byte[bestAvailable];
            int c;
            while ((c = bis.read(buf, 0, buf.length)) != -1) {
                String string = new String(buf, 0, c, FileGlobal.UTF8);
                System.out.println(string);
            }
        }
        bis.close();
    }
}
