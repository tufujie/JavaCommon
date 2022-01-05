package com.jef.sorted;

import com.jef.algorithm.sort.ArraySortUtil;
import com.jef.constant.BasicConstant;
import com.jef.util.PrintUtil;

import org.junit.Test;

/**
 * 排序算法
 * 可参考博客：https://www.cnblogs.com/tufujie/p/5041665.html
 * @author Jef
 * @date 2021/4/13
 */
public class ArraySortUtilTest {

    @Test
    public void testBubbleSort() {
        Integer[] array = ArraySortUtil.bubbleSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testSimpleChooseSort() {
        Integer[] array = ArraySortUtil.simpleChooseSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testSelectionSort() {
        Integer[] array = ArraySortUtil.selectionSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testSelectionSortV2() {
        Integer[] array = ArraySortUtil.selectionSortV2(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testBucketSort() {
        Integer[] array = ArraySortUtil.bucketSort(BasicConstant.INTEGER_ARRAY);
        for (Integer i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void testDirectInsertSort() {
        Integer[] array = ArraySortUtil.directInsertSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testInsertSort() {
        Integer[] array = ArraySortUtil.insertSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testShellSort() {
        Integer[] array = ArraySortUtil.shellSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testShellSortV2() {
        Integer[] array = ArraySortUtil.shellSortV2(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testBinaryInsertSrot() {
        Integer[] array = ArraySortUtil.binaryInsertSrot(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testQuickSort() {
        Integer[] array = ArraySortUtil.quickSort(BasicConstant.INTEGER_ARRAY, 0, BasicConstant.INTEGER_ARRAY.length - 1);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testCountSort() {
        Integer[] array = ArraySortUtil.countSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testMergeSort() {
        Integer[] array = ArraySortUtil.mergeSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testHeapSort() {
        Integer[] array = ArraySortUtil.buildbigheap(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testHeapSortV2() {
        Integer[] array = ArraySortUtil.buildbigheapV2(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testBucketSortV2() {
        // 这里桶的数量可以你自己定义，这里我就定义成了4
        Integer[] array = ArraySortUtil.bucketsort(BasicConstant.INTEGER_ARRAY, 4);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testRadixSort() {
        Integer[] array = ArraySortUtil.radixSort(BasicConstant.INTEGER_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

}