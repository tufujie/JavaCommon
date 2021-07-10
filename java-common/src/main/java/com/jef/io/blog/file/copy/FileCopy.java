package com.jef.io.blog.file.copy;

import com.jef.io.blog.FileGlobal;
import com.jef.io.blog.in.BufferedReaderTest;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 用于复制文件内容，from.txt文件内容来源，to.txt文件内容复制去处
 * @author Jef
 */
public class FileCopy {
    private static final int BSIZE = 1024;
    public static void copyFile(String fromFile, String toFile) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fromFile));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(toFile));
        int in;
        while ((in = bis.read()) != -1) {
            bos.write(in);
        }
        // 清空缓冲区
        bos.flush();
        bis.close();
        bos.close();
    }

    public static void copyFileTwo(String fromFile, String toFile) throws IOException {
        FileReader fr = new FileReader(fromFile);
        FileWriter fw = new FileWriter(toFile);
        char[] buffer = new char[8 * BSIZE];
        int c;
        while ((c = fr.read(buffer, 0, buffer.length)) != -1) {
            String string = new String(buffer, 0, c);
            System.out.print(string);
            fw.write(buffer, 0, c);
        }
        fw.flush();
        fr.close();
        fw.close();
    }

    public static void copyFileThree(String fromFile, String toFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fromFile));
        BufferedWriter bw = new BufferedWriter(new FileWriter(toFile));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            bw.write(line);
            // 添加该换行时进行换行
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

    public static void copyFileFour(String fromFile, String toFile) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(BufferedReaderTest.read(fromFile)));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(toFile)));
        // 等价于下面
        //        PrintWriter pw = new PrintWriter(toFile);
        String line;
        while ((line = br.readLine()) != null) {
            pw.write(line + "\n");
        }
        pw.flush();
        br.close();
        pw.close();
    }

    public static void copyFileFive(String fromFile, String toFile) throws IOException {
        FileChannel
                from = new FileInputStream(fromFile).getChannel(),
                to = new FileOutputStream(toFile).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        while (from.read(buff) != -1) {
            buff.flip();
            to.write(buff);
            buff.clear();
        }
    }

    public static void copyFileSix(String fromFile, String toFile) throws IOException {
        FileChannel
                from = new FileInputStream(fromFile).getChannel(),
                to = new FileOutputStream(toFile).getChannel();
        from.transferTo(0, from.size(), to);
        to.transferFrom(from, 0, from.size());

    }



    public static void main(String[] args) throws IOException {
        FileCopy.copyFileSix(FileGlobal.FROMFILENAME, FileGlobal.TOFILENAME);
    }

}