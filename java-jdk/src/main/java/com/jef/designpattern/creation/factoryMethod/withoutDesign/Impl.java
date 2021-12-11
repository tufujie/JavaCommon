package com.jef.designpattern.creation.factoryMethod.withoutDesign;

import com.jef.util.PrintUtil;

/**
 * 接口，输出文本实现
 * @author Jef
 * @create 20180707
 */
public class Impl implements Api {
    @Override
    public void test(String text) {
        PrintUtil.outPrint(text);
    }
}
