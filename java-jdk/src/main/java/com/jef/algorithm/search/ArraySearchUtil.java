package com.jef.algorithm.search;

import java.util.Arrays;

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

    public static int maxSize = 20;

    //首先得到一个斐波那契数列，mid的值是low+F(k-1)-1
    public static int[] fibonacciSequence() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找
     *
     * @param array 传入数组
     * @param value 待搜索的值
     * @return 下标
     */
    public static int fibonacciSearch(int[] array, int value) {
        int left = 0;
        int right = array.length - 1;
        // 表示斐波那契分割数值的下标
        int fibIndex = 0;
        // 存放黄金分割点的值
        int mid;
        // 存放斐波那契数列
        int[] fibArray = fibonacciSequence();//得到斐波那契数列
        // 获取到斐波那契分割数值的下标，找到k值
        while (right > fibArray[fibIndex] - 1) {
            fibIndex++;
        }
        // 复制到新的数组，fibArray[fibIndex]的值可能大于array的长度，因此需要构建一个新数组，不足的部分会使用0填充
        int[] temp = Arrays.copyOf(array, fibArray[fibIndex]);
        // 得到f[k]的值可能会大于a的长度,将新数组的多余的部分用数组中的最后一个数字填充
        // 将新填充的内容替换为最后的数
        // 例：temp = {1,3,4,6,9,11,0,0} => {1,3,4,6,9,11,11,11}
        for (int i = right + 1; i < temp.length; i++) {
            temp[i] = array[right];
        }
        // 使用while来循环处理，找到value，前提是左指针在右指针前边
        // 满足这个条件就可以继续找
        while (left <= right) {
            // 找到的黄金分割点
            mid = left + fibArray[fibIndex - 1] - 1;
            // 查找的值小于该点的时候就从前往后找，当查找的值小于当前值时应该向数组的前边遍历
            if (value < temp[mid]) {
                right = mid - 1;
                // 斐波那契数向前移一位
                fibIndex--;
            }
            // 当查找的值小于当前值时应该向数组的后边遍历
            else if (value > temp[mid]) {
                left = mid + 1;
                fibIndex -= 2;
            } else {
                return Math.min(mid, right);
            }
        }
        return -1;
    }

    /**
     * 在⼀个⼆维数组中，每⼀⾏都按照从左到右递增的顺序排序，每⼀列都按照从上到下递增的顺序排序。请完成⼀个函数，输⼊这样的⼀个⼆维数组和⼀个整数，判断数组中是否含有该整数。
     */
    public static boolean array2Find(int[][] array, int target) {
        //基本思路从左下⻆开始找，这样速度最快
        int row = array.length - 1;//⾏
        int column = 0;//列
        //当⾏数⼤于0，当前列数⼩于总列数时循环条件成⽴
        while ((row >= 0) && (column < array[0].length)) {
            if (array[row][column] > target) {
                row--;
            } else if (array[row][column] < target) {
                column++;
            } else {
                return true;
            }
        }
        return false;
    }

}