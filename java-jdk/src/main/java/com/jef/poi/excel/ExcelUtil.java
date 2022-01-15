package com.jef.poi.excel;

import com.jef.io.blog.FileGlobal;
import com.jef.util.NumberUtils;

import com.google.common.collect.Lists;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.record.cf.FontFormatting;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Jef
 * @date 2019/5/20
 */
public class ExcelUtil {
    /**
     * 输出到文件
     * @param workbook Excel文件(Workbook)
     * @throws IOException
     */
    private static void outToFile(HSSFWorkbook workbook) throws IOException {
        FileOutputStream out = new FileOutputStream(FileGlobal.POI_EXCEL);
        // 保存Excel文件
        workbook.write(out);
        // 关闭文件流
        out.close();
        System.out.println("OK!");
    }

    /**
     * 基础功能
     * @author Jef
     * @date 2019/5/21
     * @param
     * @return void
     */
    protected static void basic() throws IOException {
        // 创建Workbook和Sheet
        // 创建Excel文件(Workbook)
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表(Sheet)
        HSSFSheet sheet = workbook.createSheet("Test");
        // 创建单元格
        // 创建行,从0开始
        HSSFRow row = sheet.createRow(0);
        // 创建行的单元格,也是从0开始
        HSSFCell cell = row.createCell(0);
        // 设置单元格内容
        cell.setCellValue("李志伟");
        // 设置单元格内容,重载
        row.createCell(1).setCellValue(false);
        // 设置单元格内容,重载
        row.createCell(2).setCellValue(new Date());
        // 设置单元格内容,重载
        row.createCell(3).setCellValue(12.345);
        // 创建文档摘要信息
        // 创建文档信息
        workbook.createInformationProperties();
        // 摘要信息
        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
        // 类别
        dsi.setCategory("类别-Excel文件");
        // 管理者
        dsi.setManager("管理者-Jef");
        // 公司
        dsi.setCompany("公司-北辰云科技");
        // 摘要信息
        SummaryInformation si = workbook.getSummaryInformation();
        // 主题
        si.setSubject("主题-excel主题");
        // 标题
        si.setTitle("标题-测试文档");
        // 作者
        si.setAuthor("作者-Jef");
        // 备注
        si.setComments("备注-POI测试文档");
        // 创建批注
        HSSFPatriarch patr = sheet.createDrawingPatriarch();
        // 创建批注位置
        HSSFClientAnchor anchor = patr.createAnchor(0, 0, 0, 0, 5, 1, 8, 3);
        // 创建批注
        HSSFComment comment = patr.createCellComment(anchor);
        // 设置批注内容
        comment.setString(new HSSFRichTextString("这是这个单元格的批注！"));
        // 设置批注作者
        comment.setAuthor("Jef");
        // 设置批注默认显示
        comment.setVisible(false);
        cell = sheet.createRow(2).createCell(1);
        cell.setCellValue("测试添加批注");
        // 把批注赋值给单元格
        cell.setCellComment(comment);
        // 得到页眉
        HSSFHeader header = sheet.getHeader();
        header.setLeft("页眉左边");
        header.setRight("页眉右边");
        header.setCenter("页眉中间");
        // 得到页脚
        HSSFFooter footer = sheet.getFooter();
        footer.setLeft("页脚左边");
        footer.setRight("页脚右边");
        footer.setCenter("页脚中间");
        outToFile(workbook);
    }

    /**
     * 设置格式
     */
    protected static void setStyle() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        // 设置日期格式--使用Excel内嵌的格式
        HSSFCell cell = row.createCell(0);
        cell.setCellValue(new Date());
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        cell.setCellStyle(style);
        // 设置保留2位小数--使用Excel内嵌的格式
        cell = row.createCell(1);
        cell.setCellValue(12.3456789);
        style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        cell.setCellStyle(style);
        // 设置货币格式--使用自定义的格式
        cell = row.createCell(2);
        cell.setCellValue(12345.6789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("￥#,##0"));
        cell.setCellStyle(style);
        // 设置百分比格式--使用自定义的格式
        cell = row.createCell(3);
        cell.setCellValue(0.123456789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        cell.setCellStyle(style);
        // 设置中文大写格式--使用自定义的格式
        cell = row.createCell(4);
        cell.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("[DbNum2][$-804]0"));
        cell.setCellStyle(style);
        // 设置科学计数法格式--使用自定义的格式
        cell = row.createCell(5);
        cell.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00E+00"));
        cell.setCellStyle(style);
        outToFile(workbook);
    }

    /**
     * 合并列和行
     */
    protected static void mergeCellAndMergeRow() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        // 合并列
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("合并列");
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(region);
        // 合并行
        cell = row.createCell(6);
        cell.setCellValue("合并行");
        region = new CellRangeAddress(0, 5, 6, 6);
        sheet.addMergedRegion(region);
        outToFile(workbook);
    }

    /**
     * 单元格对齐
     */
    public static void setAlign() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("单元格对齐");
        HSSFCellStyle style = workbook.createCellStyle();
        // 水平居中
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 垂直居中
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 自动换行
        style.setWrapText(true);
        // 缩进
        style.setIndention((short)5);
        // 文本旋转，这里的取值是从-90到90，而不是0-180度。
        style.setRotation((short)60);
        cell.setCellStyle(style);
        outToFile(workbook);
    }

    /**
     * 设置边框
     */
    protected static void setBorder() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(1);
        cell.setCellValue("设置边框");
        HSSFCellStyle style=workbook.createCellStyle();
        // 上边框
        style.setBorderTop(HSSFCellStyle.BORDER_DOTTED);
        // 下边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THICK);
        // 左边框
        style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);
        // 右边框
        style.setBorderRight(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);
        // 上边框颜色
        style.setTopBorderColor(HSSFColor.RED.index);
        // 下边框颜色
        style.setBottomBorderColor(HSSFColor.BLUE.index);
        // 左边框颜色
        style.setLeftBorderColor(HSSFColor.GREEN.index);
        // 右边框颜色
        style.setRightBorderColor(HSSFColor.PINK.index);
        cell.setCellStyle(style);
        outToFile(workbook);
    }

    /**
     * 设置字体
     */
    protected static void setFont() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(1);
        cell.setCellValue("设置字体");
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        // 设置字体名称
        font.setFontName("华文行楷");
        // 设置字号
        font.setFontHeightInPoints((short)28);
        // 设置字体颜色
        font.setColor(HSSFColor.RED.index);
        // 设置下划线
        font.setUnderline(FontFormatting.U_SINGLE);
        // 设置上标下标
        font.setTypeOffset(FontFormatting.SS_SUPER);
        // 设置删除线
        font.setStrikeout(true);
        style.setFont(font);
        cell.setCellStyle(style);
        outToFile(workbook);
    }

    /**
     * 设置背景颜色
     */
    protected static void setBackground() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(1);
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置图案颜色
        style.setFillForegroundColor(HSSFColor.GREEN.index);
        // 设置图案背景色
        style.setFillBackgroundColor(HSSFColor.RED.index);
        // 设置图案样式
        style.setFillPattern(HSSFCellStyle.SQUARES);
        cell.setCellStyle(style);
        outToFile(workbook);
    }

    /**
     * 设置宽高
     */
    protected static void setWidthAndHeight() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(1);
        cell.setCellValue("123456789012345678901234567890");
        // 设置第一列的宽度是31个字符宽度
        sheet.setColumnWidth(1, 31 * 256);
        // 设置行的高度是50个点
        row.setHeightInPoints(50);
        outToFile(workbook);
    }

    /**
     * 判断
     */
    protected static void judge() {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(1);
        // 设置日期数据
        cell.setCellValue(new Date());
        // 输出：false
        System.out.println(DateUtil.isCellDateFormatted(cell));
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        // 设置日期样式
        cell.setCellStyle(style);
        // 输出：true
        System.out.println(DateUtil.isCellDateFormatted(cell));
    }

    /**
     * 设置公式
     */
    protected static void formula() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        // 设置公式
        cell.setCellFormula("2+3*4");
        cell = row.createCell(1);
        cell.setCellValue(10);
        cell = row.createCell(2);
        // 设置公式
        cell.setCellFormula("A1*B1");
        row = sheet.createRow(1);
        row.createCell(0).setCellValue(1);
        row.createCell(1).setCellValue(2);
        row.createCell(2).setCellValue(3);
        row.createCell(3).setCellValue(4);
        row.createCell(4).setCellValue(5);
        // SUM函数
        row = sheet.createRow(2);
        // 等价于"A2+C2"
        row.createCell(0).setCellFormula("sum(A2,C2)");
        // 等价于"B1+C1+D1"
        row.createCell(1).setCellFormula("sum(B2:D2)");
        // 日期函数
        HSSFCellStyle style=workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("yyyy-mm-dd"));
        row = sheet.createRow(3);
        // 日历对象
        Calendar date = Calendar.getInstance();
        cell = row.createCell(0);
        date.set(2011, 2, 7);
        cell.setCellValue(date.getTime());
        // 第一个单元格开始时间设置完成
        cell.setCellStyle(style);
        cell = row.createCell(1);
        date.set(2014, 4, 25);
        cell.setCellValue(date.getTime());
        // 第一个单元格结束时间设置完成
        cell.setCellStyle(style);
        cell = row.createCell(2);
//        cell.setCellFormula("CONCATENATE(DATEDIF(A4,B4,\"y\"),\"年\")");
        cell = row.createCell(3);
//        cell.setCellFormula("CONCATENATE(DATEDIF(A4,B4,\"m\"),\"月\")");
        cell = row.createCell(4);
//        cell.setCellFormula("CONCATENATE(DATEDIF(A4,B4,\"d\"),\"日\")");
        // 字符串相关函数
        row = sheet.createRow(4);
        row.createCell(0).setCellValue("abcdefg");
        row.createCell(1).setCellValue("ABCDEF");
        row.createCell(2).setCellValue("aa bb cc dd ee fF GG");
        row.createCell(3).setCellFormula("UPPER(A5)");
        row.createCell(4).setCellFormula("LOWER(B5)");
        row.createCell(5).setCellFormula("PROPER(C5)");
        // IF函数
        row = sheet.createRow(5);
        row.createCell(0).setCellValue(12);
        row.createCell(1).setCellValue(23);
        row.createCell(3).setCellFormula("IF(A6>B6,\"A6大于B6\",\"A6小于等于B6\")");
        // CountIf和SumIf函数
        row = sheet.createRow(6);
        row.createCell(0).setCellValue(57);
        row.createCell(1).setCellValue(89);
        row.createCell(2).setCellValue(56);
        row.createCell(3).setCellValue(67);
        row.createCell(4).setCellValue(60);
        row.createCell(5).setCellValue(73);
        row.createCell(7).setCellFormula("COUNTIF(A7:F7,\">=60\")");
        row.createCell(8).setCellFormula("SUMIF(A7:F7,\">=60\",A7:F7)");
        // Lookup函数
        row = sheet.createRow(7);
        row.createCell(0).setCellValue(0);
        row.createCell(1).setCellValue(59);
        row.createCell(2).setCellValue("不及格");
        row = sheet.createRow(8);
        row.createCell(0).setCellValue(60);
        row.createCell(1).setCellValue(69);
        row.createCell(2).setCellValue("及格");
        row = sheet.createRow(9);
        row.createCell(0).setCellValue(70);
        row.createCell(1).setCellValue(79);
        row.createCell(2).setCellValue("良好");
        row = sheet.createRow(10);
        row.createCell(0).setCellValue(80);
        row.createCell(1).setCellValue(100);
        row.createCell(2).setCellValue("优秀");
        row = sheet.createRow(11);
        row.createCell(0).setCellValue(75);
        row.createCell(1).setCellFormula("LOOKUP(A12,$A$8:$A$11,$C$8:$C$11)");
        row.createCell(2).setCellFormula("VLOOKUP(A12,$A$8:$C$11,3,true)");
        // 随机数函数
        row = sheet.createRow(12);
        // 取0-1之间的随机数
        row.createCell(0).setCellFormula("RAND()");
        // 取0-100之间的随机整数
        row.createCell(1).setCellFormula("int(RAND()*100)");
        // 取10-20之间的随机实数
        row.createCell(2).setCellFormula("rand()*10+10");
        // 随机小写字母
        row.createCell(3).setCellFormula("CHAR(INT(RAND()*26)+97)");
        // 随机大写字母
        row.createCell(4).setCellFormula("CHAR(INT(RAND()*26)+65)");
        // 随机大小写字母
        row.createCell(5).setCellFormula("CHAR(INT(RAND()*26)+if(INT(RAND()*2)=0,97,65))");
        // 获得公式的返回值
        row = sheet.createRow(13);
        // A14
        row.createCell(0).setCellValue(7);
        // B14
        row.createCell(1).setCellValue(8);
        row.createCell(2).setCellFormula("A14*B14+14");
        HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
        // 若Excel文件不是POI创建的，则不必调用此方法
        // cell = e.evaluateInCell(cell);
        System.out.println("公式计算结果：" + cell.getNumericCellValue());
        outToFile(workbook);
    }

    protected static void useDiagram() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        // 画线
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short)1, 0,(short)4, 4);
        HSSFSimpleShape line = patriarch.createSimpleShape(anchor);
        // 设置图形类型
        line.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
        // 设置图形样式
        line.setLineStyle(HSSFShape.LINESTYLE_SOLID);
        // 在POI中线的宽度12700表示1pt,所以这里是0.5pt粗的线条。
        line.setLineWidth(6350);
        // 画矩形
        anchor = new HSSFClientAnchor(255, 122, 255, 122, (short)0, 5,(short)4, 7);
        HSSFSimpleShape rec = patriarch.createSimpleShape(anchor);
        rec.setShapeType(HSSFSimpleShape.OBJECT_TYPE_RECTANGLE);
        // 设置边框样式
        rec.setLineStyle(HSSFShape.LINESTYLE_DASHGEL);
        // 设置填充色
        rec.setFillColor(255, 0, 0);
        // 设置边框宽度
        rec.setLineWidth(25400);
        // 设置边框颜色
        rec.setLineStyleColor(0, 0, 255);
        // 画圆形
        // 设置图片类型
        anchor = new HSSFClientAnchor(255, 122, 255, 122, (short)0, 9,(short)4, 11);
        rec = patriarch.createSimpleShape(anchor);
        rec.setShapeType(HSSFSimpleShape.OBJECT_TYPE_OVAL);
        // 设置边框样式
        rec.setLineStyle(HSSFShape.LINESTYLE_DASHGEL);
        // 设置填充色
        rec.setFillColor(255, 0, 0);
        // 设置边框宽度
        rec.setLineWidth(25400);
        // 设置边框颜色
        rec.setLineStyleColor(0, 0, 255);
        outToFile(workbook);
    }

    protected static void useDiagramV2() throws IOException {
        // 画Grid
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        HSSFRow row = sheet.createRow(2);
        row.createCell(1);
        row.setHeightInPoints(240);
        sheet.setColumnWidth(2, 9000);
        int linesCount = 20;
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 因为HSSFClientAnchor中dx只能在0-1023之间,dy只能在0-255之间，这里采用比例的方式
        double xRatio = 1023.0 / (linesCount * 10);
        double yRatio = 255.0 / (linesCount * 10);
        // 画竖线
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 200;
        for (int i = 0; i < linesCount; i++) {
            HSSFClientAnchor a2 = new HSSFClientAnchor();
            a2.setAnchor((short) 2, 2, (int) (x1 * xRatio),
                    (int) (y1 * yRatio), (short) 2, 2, (int) (x2 * xRatio),
                    (int) (y2 * yRatio));
            HSSFSimpleShape shape2 = patriarch.createSimpleShape(a2);
            shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
            x1 += 10;
            x2 += 10;
        }
        // 画横线
        x1 = 0;
        y1 = 0;
        x2 = 200;
        y2 = 0;
        for (int i = 0; i < linesCount; i++) {
            HSSFClientAnchor a2 = new HSSFClientAnchor();
            a2.setAnchor((short) 2, 2, (int) (x1 * xRatio),
                    (int) (y1 * yRatio), (short) 2, 2, (int) (x2 * xRatio),
                    (int) (y2 * yRatio));
            HSSFSimpleShape shape2 = patriarch.createSimpleShape(a2);
            shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
            y1 += 10;
            y2 += 10;
        }
        outToFile(workbook);
    }

    /**
     * excel表操作
     * @author Jef
     * @date 2019/5/20
     * @param
     * @return void
     */
    protected void excelOperate() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表(Sheet)
        workbook.createSheet("Test0");
        // 创建工作表(Sheet)
        workbook.createSheet("Test1");
        // 创建工作表(Sheet)
        workbook.createSheet("Test2");
        // 创建工作表(Sheet)
        workbook.createSheet("Test3");
        // 设置默认工作表
        workbook.setActiveSheet(2);
        // 重命名工作表
        // 创建工作表(Sheet)
        workbook.createSheet("Test0");
        // 创建工作表(Sheet)
        workbook.createSheet("Test1");
        // 创建工作表(Sheet)
        workbook.createSheet("Test2");
        // 创建工作表(Sheet)
        workbook.createSheet("Test3");
        // 重命名工作表
        workbook.setSheetName(2, "1234");
        // 调整表单显示比例
        // 创建工作表(Sheet)
        HSSFSheet sheet1= workbook.createSheet("Test0");
        // 创建工作表(Sheet)
        HSSFSheet sheet2 = workbook.createSheet("Test1");
        // 创建工作表(Sheet)
        HSSFSheet sheet3 = workbook.createSheet("Test2");
        // 50%显示比例
        sheet1.setZoom(1, 2);
        // 200%显示比例
        sheet2.setZoom(2, 1);
        // 10%显示比例
        sheet3.setZoom(1, 10);
        // 显示/隐藏网格线
        sheet1= workbook.createSheet("Test0");
        sheet2 = workbook.createSheet("Test1");
        // 隐藏Excel网格线,默认值为true
        sheet1.setDisplayGridlines(false);
        // 打印时显示网格线,默认值为false
        sheet2.setGridsPrinted(true);
        // 遍历Sheet
        FileInputStream stream = new FileInputStream(FileGlobal.POI_EXCEL);
        //读取现有的Excel
        workbook = new HSSFWorkbook(stream);
        // 得到指定名称的Sheet
        HSSFSheet sheet = workbook.getSheet("Test0");
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Excel行列操作
     * @author Jef
     * @date 2019/5/20
     * @param
     * @return void
     */
    protected static void rowColumnOperate() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Test");
        // 组合行
        sheet.groupRow(1, 3);
        // 组合行
        sheet.groupRow(2, 4);
        // 组合列
        sheet.groupColumn(2, 7);
        // 锁定列
        // 冻结行列
        sheet.createFreezePane(2, 3, 15, 25);
        // 上下移动行
        FileInputStream stream = new FileInputStream(FileGlobal.POI_EXCEL);
        workbook = new HSSFWorkbook(stream);
        sheet = workbook.getSheet("Test0");
        // 把第3行到第4行向下移动两行
        sheet.shiftRows(2, 4, 2);
        outToFile(workbook);
    }

    /**
     * 设置密码
     * @author Jef
     * @date 2019/5/20
     * @param
     * @return void
     */
    protected static void setPassword() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet= workbook.createSheet("Test0");
        HSSFRow row=sheet.createRow(1);
        HSSFCell cell=row.createCell(1);
        cell.setCellValue("已锁定");
        HSSFCellStyle locked = workbook.createCellStyle();
        // 设置锁定
        locked.setLocked(true);
        cell.setCellStyle(locked);
        cell=row.createCell(2);
        cell.setCellValue("未锁定");
        HSSFCellStyle unlocked = workbook.createCellStyle();
        // 设置不锁定
        unlocked.setLocked(false);
        cell.setCellStyle(unlocked);
        // 设置保护密码
        sheet.protectSheet("password");
        outToFile(workbook);
    }

    /**
     * 数据有效性
     * @author Jef
     * @date 2019/5/20
     * @param
     * @return void
     */
    protected static void otherFunction() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet= workbook.createSheet("Test0");
        HSSFRow row=sheet.createRow(0);
        HSSFCell cell=row.createCell(0);
        cell.setCellValue("日期列");
        // 选定一个区域
        CellRangeAddressList regions = new CellRangeAddressList(1, 65535, 0, 0);
        DVConstraint constraint = DVConstraint.createDateConstraint(DVConstraint . OperatorType . BETWEEN , "1993-01-01" ,"2014-12-31" , "yyyy-MM-dd" );
        HSSFDataValidation dataValidate = new HSSFDataValidation(regions,constraint);
        dataValidate.createErrorBox("错误", "你必须输入一个时间！");
        sheet.addValidationData(dataValidate);
        regions = new CellRangeAddressList(0, 65535, 1, 1);
        // 生成下拉式菜单
        List<String> list = Lists.newArrayList();
        list.add("Java");
        list.add("C++");
        list.add("C#");
        String[] strArray = new String[3];
        String[] stringArray = list.toArray(strArray);
        constraint = DVConstraint.createExplicitListConstraint(stringArray);
        dataValidate = new HSSFDataValidation(regions,constraint);
        sheet.addValidationData(dataValidate);
        // 得到打印对象
        HSSFPrintSetup print = sheet.getPrintSetup();
        // true，则表示页面方向为横向；否则为纵向
        print.setLandscape(false);
        // 缩放比例80%(设置为0-100之间的值)
        print.setScale((short)80);
        // 设置页宽
        print.setFitWidth((short)2);
        // 设置页高
        print.setFitHeight((short)4);
        // 纸张设置
        print.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
        // 设置打印起始页码不使用"自动"
        print.setUsePage(true);
        // 设置打印起始页码
        print.setPageStart((short)6);
        // 设置打印网格线
        sheet.setPrintGridlines(true);
        // 值为true时，表示单色打印
        print.setNoColor(true);
        // 值为true时，表示用草稿品质打印
        print.setDraft(true);
        // true表示“先行后列”；false表示“先列后行”
        print.setLeftToRight(true);
        // 设置打印批注
        print.setNotes(true);
        // Sheet页自适应页面大小
        sheet.setAutobreaks(false);

        CreationHelper createHelper = workbook.getCreationHelper();
        // 关联到网站
        Hyperlink link =createHelper.createHyperlink(Hyperlink.LINK_URL);
        link.setAddress("http://poi.apache.org/");
        sheet.createRow(10).createCell(0).setHyperlink(link);
        // 关联到当前目录的文件
        link = createHelper.createHyperlink(Hyperlink.LINK_FILE);
        link.setAddress("sample.xls");
        sheet.createRow(10).createCell(1).setHyperlink(link);
        // e-mail 关联
        link = createHelper.createHyperlink(Hyperlink.LINK_EMAIL);
        link.setAddress("mailto:poi@apache.org?subject=Hyperlinks");
        sheet.createRow(10).createCell(2).setHyperlink(link);
        // 关联到工作簿中的位置
        link = createHelper.createHyperlink(Hyperlink.LINK_DOCUMENT);
        link.setAddress("'Test0'!C3");//Sheet名为Test0的C3位置
        sheet.createRow(10).createCell(3).setHyperlink(link);

        outToFile(workbook);
    }

    /**
     * 从表格获取值
     *
     * @return java.lang.String
     * @author Jef
     * @date 2022/1/6
     */
    public static String getValueFromCell(Cell cell) {
        String cellStringValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    cellStringValue += cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    cellStringValue += NumberUtils.formatByPattern(cell.getNumericCellValue(), "############.############");
                    break;
                case Cell.CELL_TYPE_ERROR:
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_STRING:
                    cellStringValue += cell.getStringCellValue().trim();
                    //.replaceAll("[\\t\\n\\r\"\'\\\\]", "") 天骄客户名称带有 \ , 取消这个字符的替换
                    cellStringValue = cellStringValue.replaceAll("[\\t\\n\\r]", "");

                    break;
                case Cell.CELL_TYPE_FORMULA:
                    try {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    } catch (Exception e) {
					/*
					cell.setCellType(Cell.CELL_TYPE_STRING); 这句可能遇到空指针,做异常捕捉
					生产遇到过如下公式报了空指针: =MID(C20, 1, 1)
					 */
                    }
                    cellStringValue += cell.getRichStringCellValue();
                    cellStringValue = cellStringValue.replaceAll("[\\t\\n\\r\"\']", "");
                    break;
                default:
                    if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
                        cellStringValue += cell.getStringCellValue();
                    } else {
                        cellStringValue += (int) cell.getNumericCellValue();
                    }
                    cellStringValue = "";
                    break;
            }
        }
        return cellStringValue.trim();
    }

}