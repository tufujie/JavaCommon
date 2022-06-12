package com.jef.algorithm;

import java.util.Scanner;

/**
 * 写出代码判断一个整数是不是2的阶次方（请代码实现，谢绝调用API方法）
 *
 * @author Jef
 * @date 2022/6/11
 */
public class Two {

    //判断整数是不是2的阶次方
    public static boolean check(int sum) {
        if (sum % 2 == 0) {
            sum = sum / 2;
            check(sum);
        } else {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        int sum = scanner.nextInt();
        System.out.println(sum + "是不是2的阶次方：" + check(sum));
    }

}