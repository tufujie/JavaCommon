package com.jef.io.blog.nio;

import com.jef.io.blog.FileGlobal;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static final int BSIZE = 1024;
    public static void main(String[] args) throws IOException {
        // 写
        FileChannel fc = new FileOutputStream(FileGlobal.DATAFILENAME).getChannel();
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();

        // 读和写
        fc = new RandomAccessFile(FileGlobal.DATAFILENAME, "rw").getChannel();
        // 移动到末尾
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap(" More text".getBytes()));
        fc.close();

        // 读
        fc = new FileInputStream(FileGlobal.DATAFILENAME).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
        while (buff.hasRemaining())
            System.out.print((char) buff.get());
    }
}
