package com.jef.sorted;

import com.jef.algorithm.sort.ArraySortUtil;
import com.jef.test.Constant;
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
        Integer[] array = ArraySortUtil.bubbleSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testSimpleChooseSort() {
        Integer[] array = ArraySortUtil.simpleChooseSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testSelectionSort() {
        Integer[] array = ArraySortUtil.selectionSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testBucketSort() {
        Integer[] array = ArraySortUtil.bucketSort(Constant.INTEGER_ARRAY);
        for (Integer i = 0; i < array.length; i++) {
            if (array[i] != null) {
                System.out.println(i);
            }
        }
    }

    @Test
    public void testDirectInsertSort() {
        Integer[] array = ArraySortUtil.directInsertSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testInsertSort() {
        Integer[] array = ArraySortUtil.insertSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testShellSort() {
        Integer[] array = ArraySortUtil.shellSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testBinaryInsertSrot() {
        Integer[] array = ArraySortUtil.binaryInsertSrot(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testQuickSort() {
        Integer[] array = ArraySortUtil.quickSort(Constant.INTEGER_ARRAY, 0, Constant.INTEGER_ARRAY.length - 1);
        PrintUtil.printArray(array);
    }

    @Test
    public void testCountSort() {
        Integer[] array = ArraySortUtil.countSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

}