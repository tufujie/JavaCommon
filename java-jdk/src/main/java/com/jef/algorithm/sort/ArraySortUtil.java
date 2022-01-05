package com.jef.algorithm.sort;

/**
 * 数组排序工具类
 * 从小到大排序
 * @author Jef
 * @date 2021/4/13
 */
public class ArraySortUtil {

    /**
     * 冒泡排序（每一趟找出最大的）
     * 性能一般
     * 冒泡排序,每一趟找出最大的，总共比较次数为array.length - 1次，每次的比较次数为array.length - i - 1次，依次递减
     * @author Jef
     * @date 2021/4/13
     * @param array
     */
    public static Integer[] bubbleSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        // 这是冒泡的另一种解法
        /*for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                // 这里-i主要是每遍历一次都把最大的i个数沉到最底下去了，没有必要再替换了
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }*/
        return array;
    }

    /**
     * 选择排序（假定某个位置的值是最小值）
     * 性能一般
     * @author Jef
     * @date 2021/4/13
     * @param array
     * @return java.lang.Integer[]
     */
    public static Integer[] simpleChooseSort(Integer[] array) {
        // 简单的选择排序
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            // 最小数的索引
            int n = i;
            for (int j = i + 1; j < array.length; j++) {
                // 找出最小的数
                if (array[j] < min) {
                    min = array[j];
                    n = j;
                }
            }
            array[n] = array[i];
            array[i] = min;

        }
        return array;
    }

    /**
     * 选择排序
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2022/1/5
     */
    public static Integer[] selectionSort(Integer[] array) {
        int min;
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            min = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            int temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        return array;
    }


    /**
     * 桶排序（桶中出现的数组元素都做个标记1，然后将桶数组中有1标记的元素依次打印）
     * 简单, 但是不用,浪费内存
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2021/4/13
     */
    public static Integer[] bucketSort(Integer[] array) {
        Integer maxValue = 0;
        for (Integer arr : array) {
            if (arr > maxValue) {
                maxValue = arr;
            }
        }
        Integer[] arrayTwo = new Integer[maxValue + 1];
        for (Integer i = 0; i < array.length; i++) {
            Integer key = array[i];
            arrayTwo[key] = 1;
        }
        return arrayTwo;
    }

    /**
     * 插入排序
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2021/4/13
     */
    public static Integer[] directInsertSort(Integer[] array) {
        // 直接插入排序
        for (int i = 1; i < array.length; i++) {
            // 待插入元素
            int temp = array[i];
            int j;
            /*
             * for (j = i-1; j>=0 && a[j]>temp; j--) { //将大于temp的往后移动一位 a[j+1] = a[j]; }
             */
            for (j = i - 1; j >= 0; j--) {
                // 将大于temp的往后移动一位
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
        return array;
    }

    /**
     * 插入排序
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2021/4/13
     */
    public static Integer[] insertSort(Integer[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            if (array[i] < array[i - 1]) {
                int val = array[i];
                int j = i - 1;
                array[j + 1] = array[j];
                while (j > 0 && val < array[j - 1]) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = val;
            }
        }
        return array;
    }

    /**
     * 希尔排序（性能最好的排序）
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2021/4/13
     */
    public static Integer[] shellSort(Integer[] array) {
        // 分组间隔设置
        int d = array.length;
        while (true) {
            d = d / 2;
            for (int x = 0; x < d; x++) {
                for (int i = x + d; i < array.length; i = i + d) {
                    int temp = array[i];
                    int j;
                    for (j = i - d; j >= 0 && array[j] > temp; j = j - d) {
                        array[j + d] = array[j];
                    }
                    array[j + d] = temp;
                }
            }
            if (d == 1) {
                break;
            }
        }
        return array;
    }

    /**
     * 二分插入排序
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2021/5/30
     */
    public static Integer[] binaryInsertSrot(Integer[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            int left = 0;
            int right = i - 1;
            int mid = 0;
            while (left <= right) {
                mid = (left + right) / 2;
                if (temp < array[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                array[j + 1] = array[j];
            }
            if (left != i) {
                array[left] = temp;
            }
        }
        return array;
    }

    /**
     * 快速排序
     *
     * @param array
     * @param left
     * @param right
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2022/1/5
     */
    public static Integer[] quickSort(Integer[] array, int left, int right) {
        if (left < right) {
            int key = array[left];
            int i = left;
            int j = right;
            // 这部分便是算法的核心思想
            while (i < j) {
                // 从右向左找到第一个不大于基准值的元素
                while (i < j && array[j] >= key) {
                    j--;
                }
                if (i < j) {
                    array[i] = array[j];
                }
                //从左往右找到第一个不小于基准值的元素
                while (i < j && array[i] <= key) {
                    i++;
                }
                if (i < j) {
                    array[j] = array[i];
                }
            }
            array[i] = key;
            // 递归继续对剩余的序列排序
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        }
        return array;
    }

    /**
     * 计数排序
     *
     * @param array
     * @return java.lang.Integer[]
     * @author Jef
     * @date 2022/1/5
     */
    public static Integer[] countSort(Integer[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        // 先找出数组中的最大值与最小值
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min)
                min = array[i];
            if (array[i] > max)
                max = array[i];
        }
        // 创建一个长度为max-min+1长度的数组来进行计数
        int[] figure = new int[max - min + 1];
        for (int i = 0; i < array.length; i++) {
            // 计算每个数据出现的次数
            figure[array[i] - min]++;
        }
        int begin = 0;
        // 创建一个新的数组来存储已经排序完成的结果
        Integer[] arrayTemp = new Integer[array.length];
        for (int i = 0; i < figure.length; i++) {
            // 循环将数据pop出来
            if (figure[i] != 0) {
                for (int j = 0; j < figure[i]; j++) {
                    arrayTemp[begin++] = min + i;
                }
            }
        }
        return arrayTemp;
    }

}