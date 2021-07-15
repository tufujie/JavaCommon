package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PrintWriterTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        String str = FileGlobal.GLOBALSTRING;
        PrintWriter fw = new PrintWriter(FileGlobal.OUTFILENAME, FileGlobal.GBK);
        fw.write(str);
        fw.println(str);
        fw.close();
    }
}
