package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.*;

public class DataInputStreamTest {
    public static void main(String[] args) throws IOException {
        readTwo(FileGlobal.OUTFILENAME);
    }

    public static void readOne(String fileName) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(
                BufferedReaderTest.read(fileName).getBytes(FileGlobal.GBK)));
        while (in.available() != 0)
            System.out.print((char) in.readByte());
    }

    public static void readTwo(String fileName) throws IOException {
        DataInputStream in = new DataInputStream(new FileInputStream(fileName));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        in.close();
    }
}
