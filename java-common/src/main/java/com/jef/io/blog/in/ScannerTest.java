package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        File file = new File(FileGlobal.INFILENAME);
        Scanner sca = null;
        try{
            sca = new Scanner(file);
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        while (sca.hasNextLine()) {
            sb.append(sca.next() + "\n");
        }
        System.out.println(sb.toString());
    }
}
