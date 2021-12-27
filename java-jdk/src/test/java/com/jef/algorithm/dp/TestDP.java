package com.jef.algorithm.dp;

import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 路径数测试
 *
 * @author Jef
 * @date 2021/12/26
 */
public class TestDP {

    /**
     * 输⼊:0 0
     * 输出: 0
     * 输⼊:3 7
     * 输出: 28
     */
    @Test
    public void testGetUniquePaths() {
        PrintUtil.printf("路径数：%s", UniquePath.getUniquePaths(0, 0));
        PrintUtil.printf("路径数：%s", UniquePath.getUniquePaths(3, 7));
    }

    @Test
    public void testGetUniquePathsOptimization() {
        PrintUtil.printf("路径数：%s", UniquePath.getUniquePathsOptimization(0, 0));
        PrintUtil.printf("路径数：%s", UniquePath.getUniquePathsOptimization(3, 7));
    }

    /**
     * 输⼊:
     * arr = [
     * [1,3,1],
     * [1,5,1],
     * [4,2,1] ]
     * 输出: 7
     */
    @Test
    public void testLeastNumber() {
        PrintUtil.printf("最短路径和：%s", MinStepNumber.getLeastNumber(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    /**
     * 示例：
     * 输⼊: word1 = "horse", word2 = "ros"
     * 输出: 3
     * 解释:
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 第二个'r')
     * rose -> ros (删除 'e')
     */
    @Test
    public void testChangeWordsLeastNumber() {
        PrintUtil.printf("最短变换数：%s", ChangeWordsMinStep.getChangeWordsMinStepNumber("horse", "ros"));
    }

    @Test
    public void testChangeWordsLeastNumberOptimization() {
        PrintUtil.printf("最短变换数：%s", ChangeWordsMinStep.getChangeWordsMinStepNumberOptimization("horse", "ros"));
    }

}