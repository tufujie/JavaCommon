package com.jef.algorithm.str;

/**
 * 替换空格
 * 两种⽅法：①常规⽅法；②利⽤ API 解决。
 * 请实现⼀个函数，将⼀个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy。则经过替换之后的字符串为We%20Are%20Happy。
 * @author Jef
 * @date 2023/4/1
 */
public class ReplaceSpace {

    /**
     * 第⼀种⽅法：常规⽅法。利⽤String.charAt(i)以及String.valueOf(char).equals(" "
     * )遍历字符串并判断元素是否为空格。是则替换为"%20",否则不替换
     */
    public static String replaceSpace(String str) {
        int length = str.length();
        // System.out.println("length=" + length);
        String result = "";
        for (int i = 0; i < length; i++) {
            char b = str.charAt(i);
            if (String.valueOf(b).equals(" ")) {
                result += "%20";
            } else {
                result += b;
            }
        }
        return result;
    }
    /**
     * 第⼆种⽅法：利⽤API替换掉所⽤空格，⼀⾏代码解决问题
     */
    public static String replaceSpace2(String str) {
        return str.replaceAll("\\s", "%20");
//        return str.replaceAll(" ", "%20");
    }

}