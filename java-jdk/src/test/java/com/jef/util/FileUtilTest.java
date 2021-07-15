package com.jef.util;

import com.google.common.collect.Maps;
import com.jef.constant.BasicConstant;
import com.jef.io.blog.FileGlobal;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * 文件工具测试
 * @author Jef
 * @date 2019/6/12
 */
public class FileUtilTest {
    String WEB_URL = "test.url";

    /**
     * 从网页文件替换内容后，生成另一个文件，不删除本地文件
     * @throws IOException
     */
    @Test
    public void testUseWebUrlNotDeleteLocal() throws Exception {
        dealwithInputStream(getInputStringFromWeb(), false);
    }

    /**
     * 替换本地文件内容，生成另一个文件，不删除本地文件
     * @throws IOException
     */
    @Test
    public void testUseLocalNotDeleteLocal() throws Exception {
        dealwithInputStream(getInputStringFromLocal(), false);
    }

    /**
     * 从网页文件替换内容后，生成另一个文件，删除本地文件
     * @throws IOException
     */
    @Test
    public void testUseWebUrlDeleteLocal() throws Exception {
        dealwithInputStream(getInputStringFromWeb(), true);
    }

    /**
     * 替换本地文件内容，生成另一个文件，删除本地文件
     * @throws IOException
     */
    @Test
    public void testUseLocalDeleteLocal() throws Exception {
        dealwithInputStream(getInputStringFromLocal(), true);
    }

    /**
     * 从网页获取流
     * @author Jef
     * @date 2019/6/12
     * @param
     * @return java.io.InputStream
     */
    private InputStream getInputStringFromWeb() {
        return FileUtil.getInputStreamByUrl(WEB_URL);
    }

    /**
     * 从本地获取流
     * @author Jef
     * @date 2019/6/12
     * @param
     * @return java.io.InputStream
     */
    private InputStream getInputStringFromLocal() throws FileNotFoundException {
        return new FileInputStream(FileGlobal.FILE_TEST_DOCX);
    }

    /**
     * 处理流数据
     * @author Jef
     * @date 2019/6/12
     * @param
     * @return java.io.InputStream
     */
    private void dealwithInputStream(InputStream in, boolean delete) throws Exception {
        XWPFDocument doc = new XWPFDocument(in);
        Map<String, Object> printInfoMap = Maps.newHashMap();
        printInfoMap.put("userName", BasicConstant.USER_NAME);
        printInfoMap.put("userPhone", BasicConstant.USER_PHONE);
        printInfoMap.put("userAge", 26);
        FileUtil.replaceDocument(doc, printInfoMap);
        FileUtil.wordToLocalUpload(doc, delete);
    }
}