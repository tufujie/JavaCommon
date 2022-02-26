package com.jef.exception;

/**
 * SOE栈异常，java.lang.StackOverflowError
 * -Xss125k
 * SOE排查：栈的深度一般为1000-2000深度，超过了深度或者超过了栈大小就会导致SOE，通过打印的日志定位错误代码位置，检测是否有无限递归，
 * 发生了死循环等情况，修改代码
 *
 * @author Jef
 * @date 2022/2/25
 */
public class SOETest {
    static int count = 0;

    public static void main(String[] args) {
        try {
            stackMethod();
        } catch (Error err) {
            err.printStackTrace();
            System.out.println("执行count=" + count);
        }
    }

    private static void stackMethod() {
        count++;
        stackMethod();
    }
}