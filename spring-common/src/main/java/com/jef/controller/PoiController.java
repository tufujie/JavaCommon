package com.jef.controller;

import com.google.common.collect.Maps;
import com.jef.constant.FileGlobal;
import com.jef.constant.PrintConstants;
import com.jef.context.REContext;
import com.jef.context.REContextManager;
import com.jef.entity.User;
import com.jef.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * poi测试控制器
 * @author Jef
 * @date 2019/5/21
 */
@Controller
@RequestMapping(value = "poiDemo")
public class PoiController {
    private static Logger logger = LogManager.getLogger(PoiController.class);

    /**
     * 其他功能的跳转
     * @param mv
     * @return
     */
    @RequestMapping(value = "poiIndex")
    public ModelAndView otherIntroduce(ModelAndView mv) {
        mv.setViewName("poi/poiIndex");
        return mv;
    }

    /**
     * 利用POI 技术动态替换word模板内容，合同下载
     * @param response
     */
    @RequestMapping(value = "/contractDownLoad", method = RequestMethod.GET)
    @ResponseBody
    public void aucLotDownLoad( HttpServletResponse response) {
        REContext context = REContextManager.getREContext();
        User loginUser = (User) context.get("loginUser");
        if (loginUser != null) {
            String userName = loginUser.getName();
            String userPhone = loginUser.getPhone();
            Integer userAge = loginUser.getAge();
            try {
                // 获取模板文件
                File demoFile = new File(FileGlobal.POI_WORD);
                InputStream in = new FileInputStream(demoFile);
                XWPFDocument doc = new XWPFDocument(in);
                // 替换读取到的word模板内容的指定字段
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                // 打印信息
                Map<String, String> printInfoMap = Maps.newHashMap();
                printInfoMap.put("userName", userName);
                printInfoMap.put("userPhone", userPhone);
                printInfoMap.put("userAge", userAge.toString());
                processParagraphs(doc, paragraphList, printInfoMap, true);
                List<XWPFHeader> pageHeaders = doc.getHeaderList();
                for (int i = 0; i < pageHeaders.size(); i++) {
                    List<XWPFParagraph> headerPara = pageHeaders.get(i).getParagraphs();
                    processParagraphs(doc, headerPara, printInfoMap, true);
                }

                List<XWPFFooter> pageFooters = doc.getFooterList();
                for (int i = 0; i < pageFooters.size(); i++) {
                    List<XWPFParagraph> footerPara = pageFooters.get(i).getParagraphs();
                    processParagraphs(doc, footerPara, printInfoMap, true);
                }
                Iterator<XWPFTable> it = doc.getTablesIterator();
                while (it.hasNext()) {
                    XWPFTable table = it.next();
                    List<XWPFTableRow> rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            List<XWPFParagraph> paragraphListTable =  cell.getParagraphs();
                            processParagraphs(doc, paragraphListTable, printInfoMap, true);
                        }
                    }
                }
                // 输出word内容文件流，提供下载
                response.setContentType("application/x-msdownload");
                String name = URLEncoder.encode("利用POI 技术动态替换word模板内容" + userName + ".docx", "UTF8");
                name = new String((name).getBytes("UTF-8"), "ISO-8859-1");
                response.addHeader("Content-Disposition", "attachment; filename*=utf-8'zh_cn'" + name);
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                ServletOutputStream servletOS = response.getOutputStream();
                doc.write(ostream);
                servletOS.write(ostream.toByteArray());
                servletOS.flush();
                servletOS.close();
            } catch (IOException e) {
                logger.error("替换文件信息异常，异常信息=" + e.getMessage(), e);
            } catch (Exception e) {
                logger.error("替换文件信息异常，异常信息=" + e.getMessage(), e);
            }
        }
    }

    /**
     * 处理段落
     * @author Jef
     * @date 2019/5/21
     * @param paragraphList 段落
     * @param printInfoMap 实际信息map
     * @param isTrue
     * @return void
     */
    private static void processParagraphs(XWPFDocument doc, List<XWPFParagraph> paragraphList, Map<String, String> printInfoMap, boolean isTrue) {
        if (paragraphList != null && paragraphList.size() > 0) {
            for (XWPFParagraph paragraph : paragraphList) {
                List<XWPFRun> runs = paragraph.getRuns();
                // 尝试这种替换会不会格式变形
                if (isTrue) {
                    String text = getResultText(paragraph.getText(), printInfoMap);
                    for (int i = 0; i < runs.size(); i++) {
                        if (i == 0) {
                            runs.get(i).setText(text, 0);
                        } else {
                            runs.get(i).setText("", 0);
                        }
                    }
                } else {
                    for (XWPFRun run : runs) {
                        String text = run.getText(0);
                        if (text != null && !"".equals(text.trim())) {
                            text = text.replaceAll("\\{(<[^>]+>\\s?)+", "{").replaceAll("(\\s?<[^>]+>)+\\}", "}");
                            boolean isSetText = false;
                            Iterator<Map.Entry<String, String>> iter = PrintConstants.printMap.entrySet().iterator();
                            while (iter.hasNext()) {
                                Map.Entry<String, String> entry = iter.next();
                                String key = entry.getKey();
                                Object value = entry.getValue();
                                if (text.contains(key)) {
                                    isSetText = true;
                                    // 文本替换
                                    text = text.replace(key, com.jef.util.StringUtils.isEmpty(printInfoMap.get(value)) ? " — —" : String.valueOf(printInfoMap.get(value)));
                                    break;
                                }
                            }
                            if (isSetText) {
                                run.setText(text, 0);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取最终文本
     * @author Jef
     * @date 2019/5/21
     * @param
     * @return java.lang.String
     */
    private static String getResultText(String text, Map<String, String> printInfoMap) {
        if (StringUtils.isNotEmpty(text)) {
            text = text.replaceAll("\\{(<[^>]+>\\s?)+", "{").replaceAll("(\\s?<[^>]+>)+\\}", "}");
            // 一行中有多个需要匹配替换的项，为了避免遍历中的map的数量过大，使用这个方式进行break
            Integer leftBrace = StringUtils.getListFromString(text, "\\{").size() - 1, rightBrace = StringUtils.getListFromString(text, "\\}").size() - 1;
            Integer oneLineNum = leftBrace.compareTo(rightBrace) >= 0 ? leftBrace : rightBrace;
            Integer countNum = 0;
            for (Map.Entry<String, String> entry : PrintConstants.printMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (text.contains(key)) {
                    // 文本替换
                    text = text.replace(key, StringUtils.isEmpty(printInfoMap.get(value)) ? " — —" : String.valueOf(printInfoMap.get(value)));
                    if ((++countNum).equals(oneLineNum)) {
                        break;
                    }
                }
            }
            return text;
        } else {
            return "";
        }
    }
}