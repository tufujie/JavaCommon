package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamTest {
    public static void main(String[] args) throws FileNotFoundException {
        new PrintStreamTest().writeTwo();
    }

    public void writeOne() throws FileNotFoundException {
        PrintStream print = new PrintStream(new FileOutputStream(FileGlobal.OUTFILENAME));
        print.println(true);
        String str = FileGlobal.GLOBALSTRING;
        print.println(str);
        print.close();
    }

    /**
     * 添加格式化输入
     * @throws FileNotFoundException
     */
    public void writeTwo() throws FileNotFoundException {
        PrintStream print = new PrintStream(new FileOutputStream(FileGlobal.OUTFILENAME));
        print.println(true);
        String str = FileGlobal.GLOBALSTRING;
        print.printf("想要输入什么：%s\n", str);
        print.close();
    }
}
