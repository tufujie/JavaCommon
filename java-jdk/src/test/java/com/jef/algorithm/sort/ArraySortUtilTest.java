package com.jef.algorithm.sort;

import com.jef.constant.BasicConstant;
import com.jef.util.PrintUtil;

import org.junit.Test;

/**
 * 排序算法
 * 可参考博客：https://www.cnblogs.com/tufujie/p/5041665.html
 *
 * @author Jef
 * @date 2021/4/13
 */
public class ArraySortUtilTest {

    @Test
    public void testBubbleSort() {
        int[] array = ArraySortUtil.bubbleSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testSimpleChooseSort() {
        int[] array = ArraySortUtil.simpleChooseSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testSelectionSort() {
        int[] array = ArraySortUtil.selectionSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testSelectionSortV2() {
        int[] array = ArraySortUtil.selectionSortV2(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testBucketSort() {
        int[] array = ArraySortUtil.bucketSort(BasicConstant.INT_ARRAY);
        for (int i = 0; i < array.length; i++) {
            System.out.println(i);
        }
    }

    @Test
    public void testBucketSortV3() {
        int[] array = ArraySortUtil.bucketSortV2(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testDirectInsertSort() {
        int[] array = ArraySortUtil.directInsertSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testInsertSort() {
        int[] array = ArraySortUtil.insertSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testShellSort() {
        int[] array = ArraySortUtil.shellSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testShellSortV2() {
        int[] array = ArraySortUtil.shellSortV2(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testBinaryInsertSrot() {
        int[] array = ArraySortUtil.binaryInsertSrot(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testQuickSort() {
        int[] array = ArraySortUtil.quickSort(BasicConstant.INT_ARRAY, 0, BasicConstant.INT_ARRAY.length - 1);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testCountSort() {
        int[] array = ArraySortUtil.countSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testMergeSort() {
        int[] array = ArraySortUtil.mergeSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testHeapSort() {
        int[] array = ArraySortUtil.buildbigheap(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testHeapSortV2() {
        int[] array = ArraySortUtil.buildbigheapV2(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testBucketSortV2() {
        // 这里桶的数量可以你自己定义，这里我就定义成了4
        int[] array = ArraySortUtil.bucketsort(BasicConstant.INT_ARRAY, 4);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testRadixSort() {
        int[] array = ArraySortUtil.radixSort(BasicConstant.INT_ARRAY);
        PrintUtil.printArray(array, "排序结果");
    }

    @Test
    public void testReOrderArray() {
        PrintUtil.printArray(ArraySortUtil.reOrderArray(BasicConstant.INT_ARRAY));
    }

}