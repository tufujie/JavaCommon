package com.jef.io.blog.nio;

import com.jef.io.blog.FileGlobal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelOutTest {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream(FileGlobal.DATAFILENAME);
        FileChannel fc = fos.getChannel();
        fc.write(ByteBuffer.wrap("some text".getBytes()));
        fc.close();
    }
}
