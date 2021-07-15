package com.jef.algorithm;

import com.google.common.collect.Lists;
import com.jef.util.TimeUtil;

import java.util.List;
import java.util.Random;

/**
 * 最大子序列求和
 * @author Jef
 * @date 2020/4/6
 */
public class MaxSubSum {

    static private int seqStart = 0;
    static private int seqEnd = -1;
    private static Random rand = new Random();

    /**
     * 最大子序列求和
     * @author Jef
     * @date 2020/4/6
     * @param integerList
     * @return int
     */
    public static int maxSubSum1(List<Integer> integerList) {
        int maxSum = 0;
        for (int i = 0; i < integerList.size(); i++) {
            for (int j = i; j < integerList.size(); j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += integerList.get(k);
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 最大子序列求和
     * @author Jef
     * @date 2020/4/6
     * @param integerList
     * @return int
     */
    public static int maxSubSum2(List<Integer> integerList) {
        int maxSum = 0;
        for (int i = 0; i < integerList.size(); i++) {
            int thisSum = 0;
            for (int j = i; j < integerList.size(); j++) {
                thisSum += integerList.get(j);
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * 最大子序列求和
     * @author Jef
     * @date 2020/4/6
     * @param integerList
     * @return int
     */
    public static int maxSubSum3(List<Integer> integerList) {
        return maxSumRec(integerList, 0, integerList.size() - 1);
    }

    /**
     * 分治算最大子序列求和
     * 例子：3 -1 2 -> 4
     * @param integerList
     * @param left
     * @param right
     * @return
     */
    private static int maxSumRec(List<Integer> integerList, int left, int right) {
        int leftBorderSum = 0, rightBorderSum = 0;
        int center = ( left + right ) / 2;
        if (left == right) {
            return integerList.get(left) > 0 ? integerList.get(left) : 0;
        }
        // 3
        int maxLeftSum  = maxSumRec(integerList, left, center);
        // 2
        int maxRightSum = maxSumRec(integerList, center + 1, right);
        int maxLeftBorderSum = 0, maxRightBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += integerList.get(i);
            if (leftBorderSum > maxLeftBorderSum) {
                // 2
                maxLeftBorderSum = leftBorderSum;
            }
        }
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += integerList.get(i);
            if (rightBorderSum > maxRightBorderSum) {
                // 2
                maxRightBorderSum = rightBorderSum;
            }
        }
        return MaxGet.getMaxOfThree(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum );
    }

    /**
     * 最大子序列求和
     * @author Jef
     * @date 2020/4/6
     * @param integerList
     * @return int
     */
    public static int maxSubSum4(List<Integer> integerList) {
        int maxSum = 0;
        int thisSum = 0;
        for (int i = 0, j = 0; j < integerList.size(); j++) {
            thisSum += integerList.get(j);
            if(thisSum > maxSum) {
                maxSum = thisSum;
                seqStart = i;
                seqEnd   = j;
            } else if(thisSum < 0) {
                i = j + 1;
                thisSum = 0;
            }
        }
        return maxSum;
    }

    /**
     * 获取算法运行时长
     * @author Jef
     * @date 2020/4/6
     * @param n
     * @param alg
     * @return void
     */
    public static void getTimingInfo(int n, int alg) {
        List<Integer> testList = Lists.newArrayList();
        long startTime = System.currentTimeMillis( );
        for(int j = 0; j < n; j++) {
            testList.add(rand.nextInt(100) - 50);
        }
        int maxSubSum = 0;
        switch (alg) {
            case 1:
                maxSubSum = maxSubSum1(testList);
                break;
            case 2:
                maxSubSum = maxSubSum2(testList);
                break;
            case 3:
                maxSubSum = maxSubSum3(testList);
                break;
            case 4:
                maxSubSum = maxSubSum4(testList);
                break;
        }
        System.out.println("maxSubSum=" + maxSubSum);
        long endTime = System.currentTimeMillis( );
        TimeUtil.printTotalTime(startTime, endTime);
    }


}