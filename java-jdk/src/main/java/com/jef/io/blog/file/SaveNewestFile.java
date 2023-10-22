package com.jef.io.blog.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件夹中保留最新的文件
 *
 * @author Jef
 * @date 2023/10/22
 */
public class SaveNewestFile {
    public static void main(String[] args) {
        String url = "D:\\Desktop\\test";
        File file = new File(url);
        saveNewestFile(file, url);
    }

    private static void saveNewestFile(File file, String url) {

        Map<String, List<String>> hashMap = new HashMap<>();
        Map<String, Long> updateTimeMap = new HashMap<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileTemp : files) {
                Long lastModified = fileTemp.lastModified();
                String fileName = fileTemp.getName();
                String fileNameBefore = fileName;
                if (fileName.contains("(") && fileName.contains(")")) {
                    fileNameBefore = fileName.substring(0, fileName.indexOf(".") - 3) + fileName.substring(fileName.indexOf("."));
                }
                List<String> fileNameList = new ArrayList<>();
                if (hashMap.containsKey(fileNameBefore)) {
                    fileNameList = hashMap.get(fileNameBefore);
                    updateTimeMap.put(fileNameBefore, Math.max(lastModified, updateTimeMap.get(fileNameBefore)));
                } else {
                    updateTimeMap.put(fileNameBefore, lastModified);
                }
                fileNameList.add(fileName);
                hashMap.put(fileNameBefore, fileNameList);
            }
        }
        Map<String, String> changeNameMap = new HashMap<>();
        for (String fileNameBefore : hashMap.keySet()) {
            List<String> fileNameList = hashMap.get(fileNameBefore);
            if (fileNameList.size() <= 1) {
                continue;
            }
            for (String fileName : fileNameList) {
                File fileTemp = new File(url + "\\" + fileName);
                if (fileTemp.lastModified() == updateTimeMap.get(fileNameBefore)) {
                    changeNameMap.put(fileName, fileNameBefore);
                } else {
                    fileTemp.delete();
                }
            }
        }
        for (String fileName : changeNameMap.keySet()) {
            File fileTemp = new File(url + "\\" + fileName);
            fileTemp.renameTo(new File(url + "\\" + changeNameMap.get(fileName)));
        }
    }

}