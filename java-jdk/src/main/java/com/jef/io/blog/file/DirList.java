package com.jef.io.blog.file;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        listMatchFiles("src\\com\\jef\\io\\blog\\file", "jsp");
    }

    /**
     * 过滤文件类
     */
    public static class DirFilter implements FilenameFilter {
        private Pattern pattern;

        public DirFilter(String regex) {
            this.pattern = Pattern.compile(regex);
        }

        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

    /**
     *  用匿名内部类的方式取代过滤文件类
     */
    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    /**
     * 提供文件路径和过滤的文件需要符合的正则表达式，列出所有符合条件的文件
     */
    public static void listMatchFiles(String filePathString, String regex) {
        File filePath = new File(filePathString);
        System.out.println("获取当前路径下的可视的所有目录");
        String[] fileList;
        if(StringUtils.isEmpty(regex)) {
            fileList = filePath.list();
        } else {
            fileList = filePath.list(new DirFilter(regex));
        }
        System.out.println("无序输出");
        for (String fileName : fileList)
            System.out.println(fileName);
        System.out.println("进行升序排序");
        Arrays.sort(fileList, String.CASE_INSENSITIVE_ORDER);
        System.out.println("升序排序后");
        for (String fileName : fileList)
            System.out.println(fileName);
    }
}
