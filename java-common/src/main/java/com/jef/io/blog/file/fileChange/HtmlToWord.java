package com.jef.io.blog.file.fileChange;

import com.jef.util.MSOfficeGeneratorUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;

/**
 * HTML转Word
 * @author Jef
 * @create 2018/6/4 19:45
 */
public class HtmlToWord {
    /**
     * html转word
     * @param htmlContent html 同理，通过这种方式字符串也能输出到word中
     * @throws Exception
     */
    public static void htmlToWord(String htmlContent) throws Exception {
        //拼一个标准的HTML格式文档
        InputStream is = new ByteArrayInputStream(htmlContent.getBytes("UTF-8"));
        File file = new File("F:\\", "test.doc");
        OutputStream os = new FileOutputStream(file);
        inputStreamToWord(is, os);
    }

    /**
     * 把is写入到对应的word输出流os中
     * 不考虑异常的捕获，直接抛出
     * @param is
     * @param os
     * @throws IOException
     */
    private static void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem();
        //对应于org.apache.poi.hdf.extractor.WordDocument
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        is.close();
    }

    /**
     * html转word，方法2
     * @param htmlContent
     * @throws Exception
     */
    public static void htmlToWordTwo(String htmlContent) throws Exception {
        try {
            Document document = Jsoup.parse(htmlContent);
            String htmlName = "F:\\test.html";
            // 将html代码写到html文件中
            FileWriter fw = new FileWriter(htmlName);
            fw.write(document.html(), 0, document.html().length());// 写入文件
            fw.flush();
            fw.close();
            String newFileName = "F:\\test.doc";
            File file = new File(newFileName);
            file.createNewFile();
            MSOfficeGeneratorUtils officeUtils = new MSOfficeGeneratorUtils(false); // 将生成过程设置为不可见
            // html文件转为word
            officeUtils.html2Word(htmlName, newFileName);
            officeUtils.saveAs(newFileName);    // 保存
            officeUtils.close(); // 关闭Office Word创建的文档
            officeUtils.quit(); // 退出Office Word程序
            File htmlFile = new File(htmlName);
            htmlFile.getAbsoluteFile().delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        htmlToWordTwo("HTML TO WORD");
    }
}
