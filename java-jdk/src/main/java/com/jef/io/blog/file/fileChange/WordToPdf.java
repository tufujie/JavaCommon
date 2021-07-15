package com.jef.io.blog.file.fileChange;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jef.io.blog.FileGlobal;
import org.docx4j.Docx4J;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.org.apache.poi.util.IOUtils;
import java.io.File;
import java.io.FileOutputStream;


/**
 * @author Jef
 * @date 2019/10/12
 */
public class WordToPdf {
    public static void main(String[] args) throws Exception {
        convertDocxToPdf(FileGlobal.DOCX_LOCAL, FileGlobal.PDF_LOCAL);

    }

    /**
     * word转pdf
     * @author Jef
     * @date 2019/10/12
     * @param wordFile
     * @param pdfFile
     * @return void
     */
    public static void wordToPdfChange(String wordFile, String pdfFile) {
        ActiveXComponent app = null;
        System.out.println("开始转换...");
        // 开始时间
        // long start = System.currentTimeMillis();
        try {
            // 打开word
            app = new ActiveXComponent("Word.Application");
            // 获得word中所有打开的文档
            Dispatch documents = app.getProperty("Documents").toDispatch();
            System.out.println("打开文件: " + wordFile);
            // 打开文档
            Dispatch document = Dispatch.call(documents, "Open", wordFile, false, true).toDispatch();
            // 如果文件存在的话，不会覆盖，会直接报错，所以我们需要判断文件是否存在
            File target = new File(pdfFile);
            if (target.exists()) {
                target.delete();
            }
            System.out.println("另存为: " + pdfFile);
            Dispatch.call(document, "SaveAs", pdfFile, 17);
            // 关闭文档
            Dispatch.call(document, "Close", false);
        } catch (Exception e) {
            System.out.println("转换失败" + e.getMessage());
        } finally {
            // 关闭office
            app.invoke("Quit", 0);
        }
    }

    public static void test2(String wordFile, String pdfFile) {
        try {
           /* Map<String, Object> param = new HashMap<String, Object>();

            XWPFDocument doc = WordUtil.generateWord(param, wordFile);
            // 在没有字体的服务器上发布要用到下面 options,同时在resource目录下加入字体文件， windows 服务器上可不加
            PdfOptions options = PdfOptions.create();
            options.fontProvider(new IFontProvider() {

                @Override
                public Font getFont(String familyName, String encoding, float size, int style, java.awt.Color color) {
                    try {
                        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
                        Font fontChinese = new Font(bfChinese, size, style, color);
                        if (familyName != null)
                            fontChinese.setFamily(familyName);
                        return fontChinese;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            });
            OutputStream out = new FileOutputStream(pdfFile);
            PdfConverter.getInstance().convert(doc, out, options);
            out.close();*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test3(String wordFile, String pdfFile) throws Exception {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new File(wordFile));
        Docx4J.toPDF(wordMLPackage, new FileOutputStream(new File(pdfFile)));

    }

    /**
     * docx文档转换为PDF
     *
     * @param pdfPath PDF文档存储路径
     * @throws Exception 可能为Docx4JException, FileNotFoundException, IOException等
     */
    public static void convertDocxToPdf(String docxPath, String pdfPath) throws Exception {

        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(docxPath);
            fileOutputStream = new FileOutputStream(new File(pdfPath));
            WordprocessingMLPackage mlPackage = WordprocessingMLPackage.load(file);
            setFontMapper(mlPackage);
            Docx4J.toPDF(mlPackage, new FileOutputStream(new File(pdfPath)));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(fileOutputStream);
        }
    }

    private static void setFontMapper(WordprocessingMLPackage mlPackage) throws Exception {
        Mapper fontMapper = new IdentityPlusMapper();
        fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
        fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
        fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
        fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
        fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
        fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
        fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
        fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
        fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
        fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
        fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
        fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
        fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
        fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));

        mlPackage.setFontMapper(fontMapper);
    }


}