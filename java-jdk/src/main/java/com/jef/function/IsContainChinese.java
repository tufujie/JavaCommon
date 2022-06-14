package com.jef.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jef on 2017-06-29.
 */
public class IsContainChinese {
    private static String regEx = "[\u4e00-\u9fa5]+";
    public static void main(String[] args) {
        System.out.println(isContainChinese1("中国China"));
        System.out.println(isContainChinese1("China"));
    }

    /**
     * 判断是否含中文方法1
     * @param str
     * @return
     */
    public static boolean isContainChinese1(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否含中文方法2
     * @param str
     */
    public static boolean isContainChinese2(String str) {
        if(str.getBytes().length == str.length())
            return false;
        return true;
    }

    /**
     * 判断是否含中文方法3
     */

    // 判断一个字符串是否含有中文
    public static boolean isContainChinese3(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c)) return true;// 有一个中文字符就返回
        }
        return false;
    }

    // 判断一个字符是否是中文
    public static boolean isChinese(char c) {
        return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
    }

    /**
     * 提取出字符串中的中文
     * @param str
     * @return
     */
    public static String returnChineseCharacter(String str)
    {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String chiResult = "";
        while (m.find()) {
            chiResult += m.group();
        }
        return chiResult;
    }

}
