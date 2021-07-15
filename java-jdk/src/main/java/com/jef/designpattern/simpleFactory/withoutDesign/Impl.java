package com.jef.designpattern.simpleFactory.withoutDesign;

import com.jef.designpattern.BasicDesign;

/**
 * 接口，输出文本实现
 * @author Jef
 * @create 20180707
 */
public class Impl implements Api {
    @Override
    public void test(String text) {
        BasicDesign.outPrint(text);
    }
}
