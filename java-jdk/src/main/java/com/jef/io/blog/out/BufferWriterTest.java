package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;

public class BufferWriterTest {
    public static void main(String[] args) throws IOException {
        String str = FileGlobal.GLOBALSTRING;
        BufferedWriter fw = new BufferedWriter(new FileWriter(FileGlobal.OUTFILENAME));
        fw.write(str);
        fw.close();
    }
}