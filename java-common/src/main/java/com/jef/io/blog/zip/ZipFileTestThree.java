package com.jef.io.blog.zip;

import com.jef.io.blog.FileGlobal;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 解压缩所有文件
 */
public class ZipFileTestThree {
    public static void main(String[] args) throws IOException {
        String basicDir = FileGlobal.ZIPDIR + File.separator + "many" + File.separator;
        File file = new File(basicDir + "manyFile.zip");
        File outFile;
        ZipFile zipFile = new ZipFile(file);
        ZipInputStream zipInput = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        InputStream input;
        OutputStream output;
        while((entry = zipInput.getNextEntry()) != null){
            System.out.println("解压缩" + entry.getName() + "文件");
            outFile = new File(basicDir + "unZip" + File.separator + entry.getName());
            if(!outFile.getParentFile().exists()){
                outFile.getParentFile().mkdir();
            } if(!outFile.exists()){
                outFile.createNewFile();
            }
            input = zipFile.getInputStream(entry);
            output = new FileOutputStream(outFile);
            int temp;
            while((temp = input.read()) != -1){
                output.write(temp);
            }
            input.close();
            output.close();
        }
    }
}
