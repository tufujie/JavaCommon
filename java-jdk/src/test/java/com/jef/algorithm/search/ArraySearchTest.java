package com.jef.algorithm.search;

import com.jef.algorithm.sort.ArraySortUtil;
import com.jef.constant.BasicConstant;
import com.jef.util.PrintUtil;

import org.junit.Test;

/**
 * 查找算法测试
 *
 * @author Jef
 * @date 2022/1/18
 */
public class ArraySearchTest {

    /**
     * 输出：5
     */
    @Test
    public void testSequenceSearch() {
        int index = ArraySearchUtil.sequenceSearch(BasicConstant.INT_ARRAY, 5);
        PrintUtil.printf("搜索结果=%s", index);
    }

    /**
     * 输出：4
     */
    @Test
    public void testBinarySearch() {
        int[] sortedArray = ArraySortUtil.bubbleSort(BasicConstant.INT_ARRAY);
        int index = ArraySearchUtil.binarySearch(sortedArray, 5);
        PrintUtil.printf("搜索结果=%s", index);
    }

    /**
     * 输出：4
     */
    @Test
    public void testInserttionSearch() {
        int[] sortedArray = ArraySortUtil.bubbleSort(BasicConstant.INT_ARRAY);
        int index = ArraySearchUtil.insertionSearch(sortedArray, 5, 0, sortedArray.length - 1);
        PrintUtil.printf("搜索结果=%s", index);
    }

    /**
     * 输出：4
     */
    @Test
    public void testFibonacciSearch() {
        int[] sortedArray = ArraySortUtil.bubbleSort(BasicConstant.INT_ARRAY);
        int index = ArraySearchUtil.fibonacciSearch(sortedArray, 5);
        PrintUtil.printf("搜索结果=%s", index);
    }

    /**
     * 输出：4
     */
    @Test
    public void testArray2Find() {
        boolean find = ArraySearchUtil.array2Find(BasicConstant.INT_ARRAY2, 5);
        System.out.println(find);
    }

}