package com.jef.designpattern.simpleFactory.withDesign;

import com.jef.designpattern.BasicDesign;

/**
 * 接口，输出文本实现
 * @author Jef
 * @create 20180707
 */
public class PhilipsScreenImpl implements IScreen {
    @Override
    public void operation(String text) {
        text = BasicDesign.PHILIPS_SCREEN;
        System.out.println("开始制造");
        BasicDesign.outPrint(text);
        System.out.println("制造结束");
        System.out.println("------");
    }
}
