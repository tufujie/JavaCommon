package com.jef.algorithm.dp;

import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 青蛙跳测试
 *
 * @author Jef
 * @date 2021/12/24
 */
public class LeapFrogJumpOfDPTest {


    @Test
    public void testJumpKindOfDp() {
        for (int i = 0; i < 10; i++) {
            PrintUtil.printf("台阶数：%s，跳法数：%s", i, LeapFrogJumpOfDP.jumpKindOfDp(i));
        }
    }

}