package com.jef.io.blog.out;
import java.io.PipedOutputStream;

public class PipedOutputStreamTest implements Runnable {
    private PipedOutputStream out = null;
    public PipedOutputStreamTest() {
        out = new PipedOutputStream();
    }
    public PipedOutputStream getOut(){
        return this.out;
    }
    public void run() {
        String message="hello , Rollen";
        try{
            out.write(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
