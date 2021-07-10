package com.jef.jsondata;

import com.jef.io.blog.FileGlobal;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;

/**
 * 从json文件读取数据
 *
 * @author Jef
 * @create 2018/6/5 19:08
 */
public class ReadJson {
    public static void main(String[] args) throws IOException {
        processTwo();
    }

    private static void processOne() throws IOException {
        File file = new File(FileGlobal.JSONDIR);
        String content = FileUtils.readFileToString(file);
        JSONObject json = new JSONObject(content);
        JSONArray majorList = json.getJSONArray("major");
        System.out.println("Jef擅长：");
        for (int i = 0; i < majorList.length(); i++) {
            System.out.println(majorList.get(i));
        }
        JSONObject life = json.getJSONObject("life");
        Integer year = (Integer) life.get("value");
        String unit = (String) life.get("unit");
        System.out.println("希望能活：" + year + unit);
    }

    private static void processTwo() throws IOException {
        File file = new File(FileGlobal.JSONDIR2);
        String content = FileUtils.readFileToString(file);
        JSONObject json = new JSONObject(content);
        JSONArray majorList = json.getJSONArray("data");
        for (int i = 0; i < majorList.length(); i++) {
            JSONObject data = (JSONObject) majorList.get(i);
            System.out.println("productCode=" + data.get("productCode"));
            System.out.println("productName=" + data.get("productName"));
        }
    }
}
