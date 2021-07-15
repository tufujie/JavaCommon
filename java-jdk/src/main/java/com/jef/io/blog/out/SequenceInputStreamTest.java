package com.jef.io.blog.out;

import com.jef.io.blog.FileGlobal;

import java.io.*;

/**
 * 将两个文件的内容合并成一个文件
 */
public class SequenceInputStreamTest {
    public static void main(String[] args) throws IOException {
        File fileOne = new File(FileGlobal.OUTFILENAME);
        File fileTwo = new File(FileGlobal.OUTFILETWONAME);
        File fileThree = new File(FileGlobal.OUTFILETHREENAME);
        InputStream inputTwo = new FileInputStream(fileTwo);
        InputStream inputThree = new FileInputStream(fileThree);
        OutputStream outputOne = new FileOutputStream(fileOne);
        // 合并流
        SequenceInputStream sis = new SequenceInputStream(inputTwo, inputThree);
        int temp;
        while((temp = sis.read()) != -1){
            outputOne.write(temp);
        }
        inputTwo.close();
        inputThree.close();
        outputOne.close();
        sis.close();
    }
}
