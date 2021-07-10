package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) throws IOException {
        String str = FileGlobal.GLOBALSTRING;
        FileWriter fw = new FileWriter(FileGlobal.OUTFILENAME);
        fw.write(str);
        fw.flush();
        fw.close();
    }
}
