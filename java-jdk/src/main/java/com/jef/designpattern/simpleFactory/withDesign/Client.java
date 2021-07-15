package com.jef.designpattern.simpleFactory.withDesign;
import com.jef.designpattern.BasicDesign;

/**
 * 客户端功能调用
 * @author Jef
 * @create 20180707
 */
public class Client {
    public static void main(String[] args) {
        // 想看三星显示器的制造工艺
        String text = BasicDesign.SAMSUNG_SCREEN;
        IScreen screen = Factory.createApi(text);
        screen.operation(text);
        // 突然又想看飞利浦的制造工艺
        text = BasicDesign.PHILIPS_SCREEN;
        screen = Factory.createApi(text);
        screen.operation(text);
    }
}
