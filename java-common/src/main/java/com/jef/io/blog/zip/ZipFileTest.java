package com.jef.io.blog.zip;

import com.jef.io.blog.FileGlobal;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipFile;

/**
 * 对压缩文件的处理
 */
public class ZipFileTest {
    public static void main(String[] args) throws IOException {
        File file = new File(FileGlobal.ZIPDIR + File.separator + "normal.zip");
        ZipFile zipFile = new ZipFile(file);
        System.out.println(zipFile.getName());
    }
}
