package com.jef.algorithm;

/**
 * 设计 Goal 解析器
 *
 * @author Jef
 * @date 2021/5/30
 */
public class Interpret {

    public static void main(String[] args) {
        System.out.println(new Interpret().interpret("G()(al)"));
        System.out.println(new Interpret().interpret("G()()()()(al)"));
        System.out.println(new Interpret().interpret("(al)G(al)()()G"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public String interpret(String command) {
        command = command.replace("()", "o");
        command = command.replace("(al)", "al");
        return command;
    }

}