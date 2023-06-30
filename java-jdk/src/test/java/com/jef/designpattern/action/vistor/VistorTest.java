package com.jef.designpattern.action.vistor;

import com.jef.designpattern.action.vistor.visitor.impl.Parent;
import com.jef.designpattern.action.vistor.visitor.impl.Principal;
import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 测试访问者模式
 *
 * @author Jef
 * @date 2021/12/12
 */
public class VistorTest {

    @Test
    public void testVistor() {
        DataView dataView = new DataView();
        PrintUtil.printf("\r\n家长视角访问学生和老师");
        dataView.show(new Parent());
        PrintUtil.printf("\r\n校长视角访问学生和老师");
        dataView.show(new Principal());
    }

}