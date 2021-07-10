package com.jef.io.blog.in;

import com.jef.io.blog.FileGlobal;

import java.io.*;

public class BufferedReaderTest {
    private BufferedReader in;
    public static void main(String[] args) throws IOException {
        System.out.println(read(FileGlobal.INFILENAME));
    }

    public static String read(String fileName) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null) {
            // 一次读一行，并不能识别换行，所以必须手动添加换行符
            sb.append(line + "\n");
        }
        in.close();
        return sb.toString();
    }

    public BufferedReaderTest(String fileName) throws Exception {
        try {
            in = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("找不到文件" + fileName);
            throw e;
        } catch (Exception e) {
            // 文件已打开，出现异常，需要清理
            try {
                in.close();
            } catch (IOException e2) {
                System.out.println("清理文件异常");
            }
            throw e;
        } finally {
            System.out.println("can do something，but need not do someting");
            // 这里一定会执行，文件不一定已打开，所以这里不执行清理工作
        }
    }

    /**
     * 读取一行
     * @return
     */
    public String getLine() {
        String line;
        try {
            line = in.readLine();
        } catch (IOException e) {
           throw new RuntimeException("读取一行失败");
        }
        return line;
    }

    /**
     * 清理工作
     */
    public void dispose() {
        try {
            in.close();
            System.out.println("清理成功");
        } catch (IOException e) {
            throw new RuntimeException("清理失败");
        }
    }
}
