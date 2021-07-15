package com.jef.util;

import org.junit.Test;

import java.io.IOException;

/**
 * @author Jef
 * @date 2021/7/9
 */
public class DownloadUtilTest {

    @Test
    public void testGetUrlTransferBase64() throws IOException {
        String url = "test.pdf";
        String base64 = DownloadUtil.getUrlTransferBase64(url);
        System.out.println("base64=");
        System.out.println(base64);
    }

    @Test
    public void testDownload() {
        String url = "test.pdf";
        String downloadSaveUrl = DownloadUtil.download(url, "D:\\download");
        System.out.println("下载后文件路径=" + downloadSaveUrl);
    }

}