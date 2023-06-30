package com.jef.designpattern.action.observer;

import com.jef.constant.BasicConstant;
import com.jef.util.PrintUtil;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

/**
 * 测试观察者模式
 *
 * @author Jef
 * @date 2021/12/11
 */
public class LookTest {

    @Test
    public void testLook() {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.draw(BasicConstant.FIRST_USER_NUMBER);
        PrintUtil.printf("测试结果：%s", JSON.toJSONString(result));
        PrintUtil.printSplitLine();
        result = lotteryService.draw(BasicConstant.SECOND_USER_NUMBER);
        PrintUtil.printf("测试结果：%s", JSON.toJSONString(result));
    }
}