package com.jef.io.blog.file.fileChange;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.jsoup.Jsoup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * HTML转pdf
 *
 * @author Jef
 * @create 2018/6/5 17:50
 */
public class HtmlToPdf {

    public static void htmlToPdf(String htmlContent) throws IOException, DocumentException {
        Document document = new Document(PageSize.LETTER);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("F:\\test2.pdf"));
        document.open();
        org.jsoup.nodes.Document documentJsoup = Jsoup.parse(htmlContent);
        String htmlName = "F:\\test.html";
        // 将html代码写到html文件中
        FileWriter fw = new FileWriter(htmlName);
        fw.write(documentJsoup.html(), 0, documentJsoup.html().length());// 写入文件
        fw.flush();
        fw.close();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(htmlName));
        document.close();
    }

    public static void main(String[] args) throws IOException, DocumentException {
        htmlToPdf("HTML TO PDF");
    }

}
