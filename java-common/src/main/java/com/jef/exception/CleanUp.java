package com.jef.exception;

import com.jef.io.blog.in.BufferedReaderTest;

/**
 * 清理工作
 * @author Jef
 * @create 2018/7/20 21:10
 */
public class CleanUp {
    public static void main(String[] args) {
        try {
            BufferedReaderTest in = new BufferedReaderTest("CleanUp.java");
            try {
                // 文件已找到，需要进行清理工作，放在finally中
                String line;
                while ((line = in.getLine()) != null) {
                    // 输出文件每一行
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.out.println("读取文件出现异常");
                e.printStackTrace();
            } finally {
                in.dispose();
            }
        } catch (Exception e) {
            System.out.println("构造失败，说明文件还没有正确读取，不用清理");
        }
    }
}
