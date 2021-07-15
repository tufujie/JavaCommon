package com.jef.io.blog.zip;

import com.jef.io.blog.FileGlobal;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 解压出一个文件
 */
public class ZipFileTestTwo {
    public static void main(String[] args) throws IOException {
            File file = new File(FileGlobal.ZIPDIR + File.separator + "normal.zip");
            File outFile = new File(FileGlobal.ZIPDIR + File.separator + "unZipNormalFile.txt");
            ZipFile zipFile = new ZipFile(file);
            ZipEntry entry = zipFile.getEntry("normal.txt");
            InputStream input = zipFile.getInputStream(entry);
            OutputStream output = new FileOutputStream(outFile);
            int temp;
            while((temp = input.read()) != -1){
                output.write(temp);
            }
            input.close();
            output.close();
    }
}
