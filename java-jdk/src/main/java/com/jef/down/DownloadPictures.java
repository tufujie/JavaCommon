package com.jef.down;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载图片
 *
 * @author Jef
 * @create 2018/6/5 17:30
 */
public class DownloadPictures {
    /**
     * 下载图片
     * @param urlString 路径
     * @param filename  保存的文件名
     * @param savePath  保存路径
     */
    public static void download(String urlString, String filename, String savePath) {
        InputStream is;
        OutputStream os;
        try {
            // 构造URL
            URL url = new URL(urlString);
            // 打开连接
            URLConnection con = url.openConnection();
            // 设置请求超时为5s
            con.setConnectTimeout(5 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(savePath);
            if(!sf.exists()){
                sf.mkdirs();
            }
            os = new FileOutputStream(sf.getPath() + "\\" + filename);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        download("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3588772980,2454248748&fm=27&gp=0.jpg",
                "test.jpg", "F:\\imgs");
    }

}
