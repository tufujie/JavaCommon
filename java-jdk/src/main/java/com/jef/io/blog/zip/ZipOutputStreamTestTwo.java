package com.jef.io.blog.zip;

import com.jef.io.blog.FileGlobal;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 压缩一个文件夹
 */
public class ZipOutputStreamTestTwo {
    public static void main(String[] args) throws IOException {
        // 要被压缩的文件夹
        String basicDir = FileGlobal.ZIPDIR + File.separator + "many";
        File file = new File(basicDir);
        File zipFile = new File(basicDir + File.separator + "manyFile.zip");
        InputStream input;
        if(file.isDirectory()){
            File[] files = file.listFiles();
            // 必须放在listFiles之后，不然这个空压缩文件也会被压缩
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOut.setComment("many zip");
            for(int i = 0; i < files.length; ++i){
                input = new FileInputStream(files[i]);
                zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
                int temp;
                while((temp = input.read()) != -1){
                    zipOut.write(temp);
                }
                input.close();
            }
            zipOut.close();
        }
    }
}
