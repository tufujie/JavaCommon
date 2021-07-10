package com.jef.array;

import com.jef.util.ArrayUtil;

/**
 * 数组初始化
 * @author Jef
 * @date 2018/10/9 19:31
 */
public class ArrayInitDemo {

    public static void main(String[] args) {
        initTwo();
    }

    /**
     * 数组初始化方式1
     * @author Jef
     * @date 2019/6/23
     * @return void
     */
    private static void initOne() {
        String[] array = new String[2];
        array[0] = "o1";
        array[1] = "o2";
        ArrayUtil.println(array);
    }

    /**
     * 数组初始化方式2
     * @author Jef
     * @date 2019/6/23
     * @return void
     */
    private static void initTwo() {
        String[] array = {"o1", "o2"};
        ArrayUtil.println(array);
    }

}
