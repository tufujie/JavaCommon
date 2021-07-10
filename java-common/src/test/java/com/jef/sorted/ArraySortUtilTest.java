package com.jef.sorted;

import com.jef.test.Constant;
import com.jef.util.ArraySortUtil;
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
    public void testSimpleChooseSort () {
        Integer[] array = ArraySortUtil.simpleChooseSort (Constant.INTEGER_ARRAY);
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
    public void testShellSort() {
        Integer[] array = ArraySortUtil.shellSort(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

    @Test
    public void testBinaryInsertSrot() {
        Integer[] array = ArraySortUtil.binaryInsertSrot(Constant.INTEGER_ARRAY);
        PrintUtil.printArray(array);
    }

}