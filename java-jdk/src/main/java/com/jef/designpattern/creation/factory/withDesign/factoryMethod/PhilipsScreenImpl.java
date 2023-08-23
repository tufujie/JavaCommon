package com.jef.designpattern.creation.factory.withDesign.factoryMethod;

import com.jef.designpattern.BasicDesign;
import com.jef.util.PrintUtil;

/**
 * 接口，输出文本实现
 * @author Jef
 * @create 20180707
 */
public class PhilipsScreenImpl implements IScreen {
    @Override
    public void operation(int n) {
        String text = BasicDesign.PHILIPS_SCREEN;
        System.out.println("开始制造" + n + "个" + text);
        System.out.println("制造结束");
        PrintUtil.printSplitLine();
    }
}
