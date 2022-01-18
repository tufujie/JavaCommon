package com.jef.algorithm.search;

/**
 * 查找算法
 *
 * @author Jef
 * @date 2022/1/18
 */
public class ArraySearchUtil {

    /**
     * 顺序查找
     *
     * @param array 数组
     * @param value 查找值
     * @return int
     * @author Jef
     * @date 2022/1/18
     */
    public static int sequenceSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找（折半查找）
     *
     * @param array 数组
     * @param value 查找值
     * @return int
     * @author Jef
     * @date 2022/1/18
     */
    public static int binarySearch(int[] array, int value) {
        int low, high, mid;
        low = 0;
        high = array.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (array[mid] == value) {
                return mid;
            }
            if (array[mid] > value) {
                high = mid - 1;
            }
            if (array[mid] < value) {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找，递归版本
     *
     * @param array 数组
     * @param value 查找值
     * @param low
     * @param high
     * @return int
     * @author Jef
     * @date 2022/1/18
     */
    public static int binarySearch2(int[] array, int value, int low, int high) {
        // 查找失败的终止条件
        if (low > high) {
            return -1;
        }
        // 这样计算简单快速
        int mid = (low + high) >> 1;
        if (array[mid] == value) {
            return mid;
        } else if (array[mid] > value) {
            return binarySearch2(array, value, low, mid - 1);
        } else {
            // else 否则编译无法通过
            return binarySearch2(array, value, mid + 1, high);
        }
    }

    /**
     * 插值查找
     *
     * @param array 数组
     * @param value 查找值
     * @param low
     * @param high
     * @return int
     * @author Jef
     * @date 2022/1/18
     */
    public static int insertionSearch(int[] array, int value, int low, int high) {
        int mid = low + (value - array[low]) / (array[high] - array[low]) * (high - low);
        if (array[mid] == value) {
            return mid;
        }
        if (array[mid] > value) {
            return insertionSearch(array, value, low, mid - 1);
        }
        if (array[mid] < value) {
            return insertionSearch(array, value, mid + 1, high);
        }
        return -1;
    }


}