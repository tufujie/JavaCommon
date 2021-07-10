package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterTest {
    public static void main(String[] args) throws IOException {
        String str = FileGlobal.GLOBALSTRING;
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(FileGlobal.OUTFILENAME));
        osw.write(str);
        osw.close();
    }
}
