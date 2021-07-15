package com.jef.io.blog.nio;

import com.jef.io.blog.FileGlobal;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelInTest {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FileGlobal.DATAFILENAME);
        FileChannel fc = fis.getChannel();
        ByteBuffer bb = ByteBuffer.allocate(FileGlobal.BSIZE);
        fc.read(bb);
        bb.flip();
        while (bb.hasRemaining())
            System.out.print((char) bb.get());
    }
}
