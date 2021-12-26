package com.jef.algorithm;

import com.jef.algorithm.recursion.LeapFrogJump;
import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 青蛙跳测试
 *
 * @author Jef
 * @date 2021/12/24
 */
public class LeapFrogJumpTest {

    @Test
    public void testJumpKind() {
        for (int i = 0; i < 10; i++) {
            int[] tempArr = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                // 初始为-1，用于判断是否被设置新值
                tempArr[j] = -1;
            }
            PrintUtil.printf("台阶数：%s，跳法数：%s", i, LeapFrogJump.jumpKind(i, tempArr));
        }
    }

    @Test
    public void testJumpKindV2() {
        for (int i = 0; i < 10; i++) {
            PrintUtil.printf("台阶数：%s，跳法数：%s", i, LeapFrogJump.jumpKindV2(i));
        }
    }

}