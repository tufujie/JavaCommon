package com.jef.other;

public class Temp {
    static int t; // 类变量
    public static void main(String[] args) {
        System.out.println(t); // 打印类变量
        int t=1; // 局部变量
        System.out.println(t); // 打印局部变量
        Temp a= new Temp(); // 创建实例
        System.out.println(a.t); // 通过实例访问实例变量
    }
}