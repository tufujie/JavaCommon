package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FileGlobal.INFILENAME);
        int bestAvailable = fis.available();
        if (bestAvailable > 0) {
            byte[] buf = new byte[bestAvailable];
            int len;
            int downloadLength = 0;
            while ((len = fis.read(buf, 0, buf.length)) != -1) {
                String string = new String(buf, 0, len, FileGlobal.UTF8);
                System.out.println(string);
                downloadLength += len;
                // 防止最后一次读取的时候，一直阻塞
                if (downloadLength >= bestAvailable) {
                    break;
                }
            }
        }
        fis.close();
    }
}
