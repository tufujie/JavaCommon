package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import com.jef.util.PrintUtil;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Supplier;

/**
 * 生产消息
 * 无参有返回值，用于生产数据
 *
 * @author Jef
 * @date 2019/3/14
 */
public class SupplierTest {

    @Test
    public void testMain() {
        /**
         * 有多行代码。每句都有分号。
         * 如果需要返回值则需要 return 语句。
         */
        Supplier<String> e1 = () -> {
            System.out.println("output1");
            System.out.println("output2");
            return BasicConstant.USER_NAME;
        };

        /**
         * 只有一行代码，且有返回值。无需分号，无需 return 语句。
         */
        Runnable e2 = () -> {
            System.out.println("notReturn()");
        };

        /**
         * 只有一行代码。无需分号。
         */
        Supplier<String> e3 = () -> "只有一行且有返回值";

    }

    @Test
    public void testGet() {
        Supplier<String> supplier = () -> BasicConstant.USER_NAME;
        System.out.println(supplier.get());
        PrintUtil.printSplitLine();
        Supplier<Integer> supplier2 = () -> {
            int[] arr = {1, 3, 4, 2, 5, 6, 7, 8};
            Arrays.sort(arr);
            return arr[arr.length - 1];
        };
        System.out.println(supplier2.get());
    }
}