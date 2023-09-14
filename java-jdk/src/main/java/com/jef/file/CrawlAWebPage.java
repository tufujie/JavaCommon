package com.jef.file;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author tufujie
 * @date 2023/9/14
 */
public class CrawlAWebPage {
    public static void main(String[] args) throws IOException {
        System.out.println("抓取开始");
        CrawlAWebPage d = new CrawlAWebPage();
        String str = d.getHtml();
        d.readHtml(str);
        System.out.println("抓取结束");
    }

    //这就是通过url获取整个页面 返回页面的字符串
    public String getHtml() throws IOException {
        StringBuffer buffer = new StringBuffer();
        //连接的url
        String urlpath = "https://www.test.cn/";
        URL url = new URL(urlpath);
        URLConnection conn = url.openConnection();
        InputStream in = conn.getInputStream();
        //字节流-》字符流 InputStreamReader
        InputStreamReader reader = new InputStreamReader(in, "utf-8");
        //按行 读出来
        BufferedReader breader = new BufferedReader(reader);
        //读取数据
        String line = "";
        while ((line = breader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer + "";
    }

    //读取页面的字符串
    public void readHtml(String html) {
        //使用Jsoup解析html 成Document对象
        Document document = Jsoup.parse(html);
        //获取页面中table 的tr部分
        Elements trs = document.select("table").select("tr");
        List list = new ArrayList<>();
        // 输出的位置
        File file = new File("E://CrawlAWebPage" + UUID.randomUUID() + ".txt");
        FileWriter fWriter = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                fWriter = new FileWriter(file);
                // 定义列数
                int column = 8;
                fWriter.append("货币名称\t现汇买入价\t现汇卖出价\t现钞卖出价\t中行折算价\t发布日期\t发布时间\r\n");
                // 具体表格内容开始的位置，这里是2
                for (int i = 2; i < trs.size(); i++) {
                    Elements tds = trs.get(i).select("td");
                    if (tds == null && tds.size() == 0 || tds.size() != column) {
                        continue;
                    }
                    // 此处可以做类型转化或者取任意一列值
                    Object[] obj = {
                            tds.get(0).text(),
                            tds.get(1).text(),
                            tds.get(2).text(),
                            tds.get(3).text(),
                            tds.get(4).text(),
                            tds.get(5).text(),
                            tds.get(6).text(),
                            tds.get(7).text()
                    };
                    list.add(obj);
                    if (tds.get(0).text().equals("美元") || tds.get(0).text().equals("欧元")) {
                        System.out.println(tds.get(0).text() + "兑换人民币汇率为" + new BigDecimal(Double.parseDouble(tds.get(1).text())
                                / 100).setScale(4, BigDecimal.ROUND_DOWN));
                    }
                    String txt = "";
                    for (int j = 0; j < tds.size(); j++) {
                        if (txt == "") {
                            txt = tds.get(j).text();
                        } else {
                            txt = txt + "\t" + tds.get(j).text();
                        }
                    }
                    fWriter.append(txt + "\r\n");
                    fWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}