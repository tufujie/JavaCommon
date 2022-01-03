package com.jef.algorithm.backTrack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 测试排列组合问题-回溯算法
 *
 * @author Jef
 * @date 2022/1/3
 */
public class BackTrackTest {

    /**
     * 测试排列组合问题
     * A：[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
     *
     * @author Jef
     * @date 2022/1/3
     */
    @Test
    public void testPermutation() {
        List<Integer> selectedNums = new ArrayList<>();
        // 参与全排列的数字
        List<Integer> nums = Arrays.asList(1, 2, 3);
        // 结果集
        List<String> result = new ArrayList<>(10);
        PermutationSolution.permutation(selectedNums, nums, result);
        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * 测试01背包问题
     * A：10
     *
     * @author Jef
     * @date 2022/1/3
     */
    @Test
    public void testKnapsack() {
        List<Integer> selectedNums = new ArrayList<>();
        // 现有物品重量
        List<Integer> weights = Arrays.asList(3, 4, 6, 8);
        // 背包最⼤承载质量
        Integer knapsackMaxWeight = 10;
        PackageMaxWeight packageMaxWeight = new PackageMaxWeight();
        packageMaxWeight.knapsack(selectedNums, weights, knapsackMaxWeight);
        System.out.println("result = " + packageMaxWeight.result);
    }

    /**
     * 测试N皇后问题-
     * A：
     *
     * @author Jef
     * @date 2022/1/3
     */
    @Test
    public void testNQueen() {
        // 8皇后问题
        int n = 8;
        int[] selectedColumn = new int[n];
        // 从第 0 ⾏开始 DFS
        NQueen.queenSettle(selectedColumn, 0, n);
    }
}