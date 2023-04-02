package com.jef.algorithm.num;

/**
 * 数值相关功能
 *
 * @author Jef
 * @date 2023/4/2
 */
public class NumberFunction {

    static boolean invalidInput = false;

    /**
     * 数值的整数次⽅
     * 给定⼀个double类型的浮点数base和int类型的整数exponent。求base的exponent次⽅。
     * ⼆分幂算法
     */
    public static double power(double base, int exponent) {
        // 如果底数等于0并且指数⼩于0
        // 由于base为double型，不能直接⽤==判断
        if (equal(base, 0.0) && exponent < 0) {
            invalidInput = true;
            return 0.0;
        }
        int absexponent = exponent;
        // 如果指数⼩于0，将指数转正
        if (exponent < 0) {
            absexponent = -exponent;
        }
        // getPower⽅法求出base的exponent次⽅。
        double res = getPower(base, absexponent);
        //如果指数⼩于0，所得结果为上⾯求的结果的倒数
        if (exponent < 0) {
            res = 1.0 / res;
        }
        return res;
    }

    // 比较两个double型变量是否相等的⽅法
    static boolean equal(double num1, double num2) {
        if (num1 - num2 > -0.000001 && num1 - num2 < 0.000001) {
            return true;
        } else {
            return false;
        }
    }

    // 求出b的e次⽅的⽅法
    static double getPower(double b, int e) {
        // 如果指数为0，返回1
        if (e == 0) {
            return 1.0;
        }
        // 如果指数为1，返回b
        if (e == 1) {
            return b;
        }
        // e>>1相等于e/2，这⾥就是求a^n =（a^n/2）*（a^n/2）
        double result = getPower(b, e >> 1);
        result *= result;
        // 如果指数n为奇数，则要再乘⼀次底数base
        if ((e & 1) == 1) {
            result *= b;
        }
        return result;
    }

    // 使⽤累乘
    public static double powerAnother(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        if (exponent >= 0) {
            return result;
        } else {
            return 1 / result;
        }
    }
}