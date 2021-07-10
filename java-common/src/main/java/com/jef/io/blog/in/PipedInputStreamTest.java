package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipedInputStreamTest implements Runnable {

    private PipedInputStream input = null;
    public PipedInputStreamTest(){
        this.input=new PipedInputStream();
    }
    public PipedInputStream getInput(){
        return this.input;
    }
    public void run(){
        byte[] b = new byte[FileGlobal.BSIZE];
        int len = 0;
        try {
            len = this.input.read(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("接受的内容为 " + (new String(b,0, len)));
    }

}
