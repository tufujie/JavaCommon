package com.jef.io.blog.file;

import com.jef.util.DateTimeUtil;
import java.io.File;
import java.util.Date;

public class AboutFileTest {
    public static void main(String[] args) {
        File file = new File("src\\com\\jef\\io\\blog\\file\\1.jsp");
        fileData(file);
        System.out.println("文件重命名");
        File fileRename = new File("src\\com\\jef\\io\\blog\\file\\rename.txt");
        File fileRenmaeTo = new File("src\\com\\jef\\io\\blog\\file\\name.txt");
        fileRename.renameTo(fileRenmaeTo);
        System.out.println("删除文件");
        File fileDelete = new File("src\\com\\jef\\io\\blog\\file\\delete.txt");
        fileDelete.delete();
    }

    private static void fileData(File file) {
        Long lastModified = file.lastModified();
        Date lastModifiedDate = new Date(lastModified);
        System.out.println("绝对路径=" + file.getAbsolutePath()
                + "\n 是否可读=" + file.canRead()
                + "\n 是否可写=" + file.canWrite()
                + "\n 名称=" + file.getName()
                + "\n 父文件夹=" + file.getParent()
                + "\n 路径=" + file.getPath()
                + "\n 长度=" + file.length()
                + "\n 最后修改时间=" + DateTimeUtil.formatDate(lastModifiedDate)
        );
        if(file.isDirectory()) {
            System.out.println("file是文件夹");
        } else {
            System.out.println("file是文件");
        }
    }
}
