package com.jef.string;

/**
 * Split用法
 *
 * @author Jef
 * @date 2018/10/13 16:55
 */
public class SplitDemo {
    public static void main(String[] args) {
        splitTwo();
    }



    public static void splitOne() {
        String strTest="1,2,";
        String[] arry = strTest.split(",");
        System.out.println(arry.length);
    }

    /**
     * 正确的做法
     */
    public static void splitTwo() {
        String strTest="1,2,";
        String[] arry = strTest.split(",", -1);
        System.out.println(arry.length);
    }

}
