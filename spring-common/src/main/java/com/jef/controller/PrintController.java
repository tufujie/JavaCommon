package com.jef.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.jef.dao.IUserDao;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.User;
import com.jef.property.cache.UserCache;
import com.jef.util.REJSONUtils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 打印测试
 * @author Jef
 * @date 20190129
 */
@Controller
@RequestMapping(value = "print")
public class PrintController {
    private static Logger logger = LogManager.getLogger(RedisController.class);

    private static Integer maxWidth = 520;
    @Autowired
    private IUserDao userDao;

    @RequestMapping(value = "/printAll")
    public ModelAndView introduce(ModelAndView mv) {
        mv.setViewName("print/printAll");
        return mv;
    }

    @RequestMapping(value = "printUser", method = RequestMethod.GET)
    @ResponseBody
    public BaseJSONVo printUser(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletRequest request, HttpServletResponse response
    ) {
        OutputStream os = null;
        try {
            String path = request.getSession().getServletContext().getRealPath("temp/pdf") + File.separator;
            String fileName = "";
            List<String> fileNameList = new ArrayList<String>();
            Integer index = 0;
            List<String> idList = Arrays.asList(ids.split(","));
            for (String id : idList) {
                fileName = System.currentTimeMillis() + "_index_" + index + ".pdf";
                File file = new File(path);
                if (!file.exists()) {
                    file.mkdirs();
                }
                // 生成PDF文件
                generatePDF(path + fileName, Long.valueOf(id));
                fileNameList.add(path + fileName);
                index++;
            }
            response.reset();
            if (fileNameList.size() > 1) {
                fileName = System.currentTimeMillis() + ".pdf";
                mergePdfFiles(fileNameList, path + fileName);
            }
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
            // 获取输出流
            os = response.getOutputStream();
            os.write(FileUtils.readFileToByteArray(new File(path + fileName)));
            os.flush();
        } catch (Exception e) {
            logger.error("打印用户信息异常，异常信息为" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    logger.error("关闭输出流时,出现异常" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return REJSONUtils.success(null, 0, "操作成功");
    }

    public void generatePDF(String filePath, Long id) throws Exception {
        User user = UserCache.getUser(userDao, id);
        if (user == null) {
            return;
        }
        try {
            // 长宽
            float width;
            float height;
            // 边距
            float left;
            float right;
            float top;
            float bottom;
            int l = 5;
            // 可设置成动态设置，从打印模板中取
            left = (float) 2.8346;
            right = (float) 2.8346;
            top = (float) 2.8346;
            bottom = (float) 2.8346;
            width = (float) 623.612;
            height = (float) 793.688;

            Rectangle pageSize = new Rectangle(width, height);
            Document document = new Document(pageSize, l + left, l + right, l + top, l + bottom);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // 票据号
            Font myFont = new Font(bfChinese, 13, Font.NORMAL);
            // 红色
            myFont.setColor(new BaseColor(234, 48, 42));
            // 普通
            Font font = new Font(bfChinese, 12, Font.NORMAL);

            int[] headWeight;
            String headWeightStr = "1,1,1,1,1,1,1,1,1,1,1,1";
            String[] headWightArr = headWeightStr.split(",");
            headWeight = new int[headWightArr.length];
            for (int i = 0; i < headWightArr.length; i++) {
                headWeight[i] = Integer.valueOf(headWightArr[i]);
            }
            // 创建一个表格
            int headLength = headWeight.length;
            PdfPTable table = createTable(headLength);
            table.setWidths(headWeight);
            table.addCell(createCell("", font, Element.ALIGN_RIGHT, 3, false));
            table.addCell(createCell("Jef", font, Element.ALIGN_CENTER, 6, false));
            table.addCell(createCell("Jef", font, Element.ALIGN_RIGHT, 3, false));

            table.addCell(createCell("用户名：" + user.getName() + "", font, Element.ALIGN_RIGHT, 4, true));
            table.addCell(createCell("电话：" + user.getPhone() + "", font, Element.ALIGN_RIGHT, 4, true));
            table.addCell(createCell("年龄：" + user.getAge() + "", font, Element.ALIGN_RIGHT, 4, true));
            PdfPCell cell = createCell("", font, Element.ALIGN_LEFT, headLength, true);
            table.addCell(cell);
            // 将表格添加到文档中
            document.add(table);
            document.newPage();
            // 关闭流
            document.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 创建表格
     * @param colNumber 列数
     * @return
     */
    public static PdfPTable createTable(int colNumber) {
        PdfPTable table = new PdfPTable(colNumber);
        try {
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
        } catch (Exception e) {
            logger.error("error", e);
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 为表格添加一个内容
     * @param value 值
     * @param font 字体
     * @param align 对齐方式
     * @param colspan 占多少列
     * @param boderFlag 是否有边框
     * @return PdfPCell 添加的文本框
     */
    protected PdfPCell createCell(String value, Font font, int align, int colspan,boolean boderFlag) {
        return createCell(value,font,align,colspan,0,boderFlag);
    }

    /**
     * 为表格添加一个内容
     * @param value 值
     * @param font 字体
     * @param align 对齐方式
     * @param colspan 占多少列
     * @param boderFlag 是否有边框
     * @return PdfPCell 添加的文本框
     */
    protected PdfPCell createCell(String value, Font font, int align, int colspan,int rowspan,boolean boderFlag) {
        return createCell(value,font,align,colspan,rowspan,boderFlag,true,-1);
    }

    /**
     * 为表格添加一个内容
     * @param value 值
     * @param font 字体
     * @param align 对齐方式
     * @param colspan 占多少列
     * @param boderFlag 是否有边框
     * @return PdfPCell 添加的文本框
     */
    protected PdfPCell createCell(String value, Font font, int align, int colspan,int rowspan,boolean boderFlag,boolean isisBottom,float minimumHeight) {
        PdfPCell cell = createCommonCell(value,font,align);
        cell.setColspan(colspan);
        if (rowspan>0) {
            cell.setRowspan(rowspan);
        }
        if (minimumHeight>0) {
            cell.setMinimumHeight(minimumHeight);
        }
        cell.setPadding(3.0f);
        if (!boderFlag) {
            cell.setBorder(0);
            cell.setPaddingTop(2f);
            if (isisBottom) {
                cell.setPaddingBottom(2f);
            }
        }
        return cell;
    }

    /*
     * 创建cell
     */
    private PdfPCell createCommonCell(String value, Font font,int align) {
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value, font));
        return cell;
    }

    /**
     * 合并pdf文档
     * @param files
     * @param newfile
     * @return
     */
    public boolean mergePdfFiles(List<String> files, String newfile) {
        boolean retValue = false;
        Document document = null;
        try {
            document = new Document(new PdfReader(files.get(0)).getPageSize(1));
            PdfCopy copy = new PdfCopy(document, new FileOutputStream(newfile));
            document.open();
            for (int i = 0; i < files.size(); i++) {
                PdfReader reader = new PdfReader(files.get(i));
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
            }
            retValue = true;
        } catch (Exception e) {
            logger.error("print", e);
        } finally {
            try {
                document.close();
            } catch (Exception e) {
                logger.error("print", e);
            }
        }
        return retValue;
    }

}