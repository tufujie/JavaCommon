package com.jef.io.blog.out;

import java.io.OutputStream;

public class OutputStreamTest {
    public static void main(String[] args) {
        OutputStream out=System.out;
        try{
            out.write("hello".getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        } try
        {
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
