package com.jef.designpattern.builder;

import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 建造者模式测试
 *
 * @author Jef
 * @date 2021/12/7
 */
public class ComputerBuilderTest {

    @Test
    public void testComputerBuilder() {
        System.out.println(ComputerBuilder.getLevelOne().getDetail());
        PrintUtil.printLineSplit();
        System.out.println(ComputerBuilder.getLevelTwo().getDetail());
    }


}