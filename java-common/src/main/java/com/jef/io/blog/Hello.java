package com.jef.io.blog;

import com.jef.io.blog.in.PipedInputStreamTest;
import com.jef.io.blog.out.PipedOutputStreamTest;

import java.io.IOException;

/*
* 测试类
* */
public class Hello {
    public static void main(String[] args) throws IOException {
        PipedOutputStreamTest send = new PipedOutputStreamTest();
        PipedInputStreamTest recive = new PipedInputStreamTest();
        try{
            //管道连接
            send.getOut().connect(recive.getInput());
        }catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(send).start();
        new Thread(recive).start();
    }
}
