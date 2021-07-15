package com.jef.poi.word;

import com.jef.io.blog.FileGlobal;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Jef
 * @date 2019/5/20
 */
public class WordUtil {
    /**
     * 输出到文件
     * @param doc Word文件
     * @throws IOException
     */
    private static void outToFile(XWPFDocument doc) throws IOException {
        FileOutputStream out = new FileOutputStream(FileGlobal.POI_WORD);
        doc.write(out);
        out.close();
        System.out.println("OK!");
    }

    protected static void basic() throws IOException {
        // 创建Word文件
        XWPFDocument doc = new XWPFDocument();
        // 新建一个段落
        XWPFParagraph p = doc.createParagraph();
        // 设置段落的对齐方式
        p.setAlignment(ParagraphAlignment.CENTER);
        // 设置下边框
        p.setBorderBottom(Borders.DOUBLE);
        // 设置上边框
        p.setBorderTop(Borders.DOUBLE);
        // 设置右边框
        p.setBorderRight(Borders.DOUBLE);
        // 设置左边框
        p.setBorderLeft(Borders.DOUBLE);
        // 创建段落文本
        XWPFRun r = p.createRun();
        r.setText("POI创建的Word段落文本");
        // 设置为粗体
        r.setBold(true);
        // 设置颜色
        r.setColor("FF0000");
        // 新建一个段落
        p = doc.createParagraph();
        r = p.createRun();
        r.setText("POI读写Word功能强大、操作简单。");
        // 创建一个表格
        XWPFTable table= doc.createTable(3, 3);
        table.getRow(0).getCell(0).setText("表格1");
        table.getRow(1).getCell(1).setText("表格2");
        table.getRow(2).getCell(2).setText("表格3");
        outToFile(doc);
    }

    /**
     * 获取word内容
     * @author Jef
     * @date 2019/5/21
     * @param
     * @return void
     */
    protected static void readInfo() throws IOException {
        FileInputStream stream = new FileInputStream(FileGlobal.POI_WORD);
        // 创建Word文件
        XWPFDocument doc = new XWPFDocument(stream);
        // 遍历段落
        for (XWPFParagraph p : doc.getParagraphs()) {
            System.out.print(p.getParagraphText());
        }
        // 遍历表格
        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for( XWPFTableCell cell : row.getTableCells()) {
                    System.out.print(cell.getText());
                }
            }
        }
    }


}