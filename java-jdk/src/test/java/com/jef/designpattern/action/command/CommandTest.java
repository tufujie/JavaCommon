package com.jef.designpattern.action.command;

import org.junit.jupiter.api.Test;

/**
 * 测试命令模式
 *
 * @author Jef
 * @date 2021/12/11
 */
public class CommandTest {

    @Test
    public void testCommand() {
        XiaoEr xiaoEr = new XiaoEr();
        xiaoEr.order(new GuangDongCuisine(new GuangDongCook()));
        xiaoEr.order(new SiChuanCussine(new SiChuanCook()));
        xiaoEr.placeOrder();
    }

}