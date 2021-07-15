package com.jef.io.blog.zip;

import com.jef.io.blog.FileGlobal;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩一个文件
 */
public class ZipOutputStreamTest {
    public static void main(String[] args) throws IOException {
        File file = new File(FileGlobal.NORMALFILENAME);
        File zipFile = new File(FileGlobal.ZIPDIR + File.separator + "normal.zip");
        InputStream input = new FileInputStream(file);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.putNextEntry(new ZipEntry(file.getName()));
        // 设置注释
        zipOut.setComment("one zip");
        int temp;
        while((temp = input.read()) != -1){
            zipOut.write(temp);
        }
        input.close();
        zipOut.close();

    }
}
