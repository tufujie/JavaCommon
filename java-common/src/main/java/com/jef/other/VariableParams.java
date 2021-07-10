package com.jef.other;

/**
 * 可变参数的使用
 *
 * @author Jef
 * @create 2018/6/5 21:12
 */
public class VariableParams {
    public static void main(String[] args) {
        test("test1", "test2");
    }

    public static void test(String... params) {
        for (String param : params) {
            System.out.println(param);
        }
    }
}
