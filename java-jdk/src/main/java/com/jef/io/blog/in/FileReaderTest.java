package com.jef.io.blog.in;


import com.jef.io.blog.FileGlobal;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) throws IOException {
        new FileReaderTest().readTwo();
    }

    public void readOne() throws IOException {
        FileReader fr = new FileReader(FileGlobal.INFILENAME);
        char[] buf = new char[FileGlobal.BSIZE];
        int len = fr.read(buf);
        String myStr = new String(buf, 0, len);
        System.out.println(myStr);
    }

    public void readTwo() throws IOException {
        FileReader fr = new FileReader(FileGlobal.INFILENAME);
        char[] buf = new char[FileGlobal.BSIZE];
        int temp, count = 0;
        while((temp = fr.read()) != -1){
            buf[count++] = (char)temp;
        }
        fr.close();
        System.out.println(new String(buf,0, count));
    }
}
