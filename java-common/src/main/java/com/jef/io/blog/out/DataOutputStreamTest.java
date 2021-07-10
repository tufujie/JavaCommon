package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.*;

public class DataOutputStreamTest {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(FileGlobal.OUTFILENAME));
        out.writeDouble(3.1415926);
        out.writeUTF("That was pi");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();
    }
}
