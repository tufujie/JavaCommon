package com.jef.algorithm.greedy;

import com.jef.algorithm.Interval;

import org.junit.jupiter.api.Test;

/**
 * 测试贪心算法
 *
 * @author Jef
 * @date 2022/1/3
 */
public class GreedyTest {

    /**
     * 测试分糖果
     * A：3
     *
     * @author Jef
     * @date 2022/1/3
     */
    @Test
    public void testShareCandy() {
        // ⼩孩对糖果的需求
        int[] gList = {1, 2, 4, 6};
        // 糖果实际⼤⼩
        int[] sList = {1, 2, 7, 3};
        int result = ShareCandy.dispatchCandy(gList, sList);
        System.out.println("result = " + result);
    }

    /**
     * 测试无重复区间
     * A：2
     *
     * @author Jef
     * @date 2022/1/3
     */
    @Test
    public void testNoDuplicateIntervas() {
        // 初始化区间
        Interval[] intervals = {
                new Interval(1, 2),
                new Interval(3, 5),
                new Interval(4, 7),
                new Interval(8, 10),
                new Interval(9, 11)
        };
        int result = NoDuplicateIntervas.removeDuplicateIntervas(intervals);
        System.out.println("result = " + result);
        result = NoDuplicateIntervas.removeSubDuplicateWithDP(intervals);
        System.out.println("result = " + result);
        result = NoDuplicateIntervas.removeSubDuplicateWithGreedy(intervals);
        System.out.println("result = " + result);
    }

}