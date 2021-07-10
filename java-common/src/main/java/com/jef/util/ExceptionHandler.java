package com.jef.util;

/**
 * 异常处理
 *
 * @author Jef
 * @date 2019/5/8
 */
public class ExceptionHandler {
    /**
     * 打印异常信息
     * @author Jef
     * @date 2019/8/20
     * @param msg
     * @param e
     * @return void
     */
    public static void printException(String msg, Exception e) {
        if (e == null) {
            return ;
        }
        StringBuffer sb = new StringBuffer();
        if (msg != null) {
            sb.append(msg).append("\n");
        }
        StackTraceElement[] m = e.getStackTrace();
        for (StackTraceElement ste : m) {
            sb.append(ste.toString());
            sb.append("\n");
        }
        sb.append("\n");
        sb.append(e.getCause());
        System.out.println(sb.toString());
    }

}