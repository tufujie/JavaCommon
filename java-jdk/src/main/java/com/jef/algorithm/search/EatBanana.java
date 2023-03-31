package com.jef.algorithm.search;

import com.jef.algorithm.MaxGet;
import com.jef.util.NumberUtils;

/**
 * 在目标时间内吃完香蕉，单位时间内最少吃几根
 */
public class EatBanana {

    /**
     * 暴力
     *
     * @param arr 香蕉堆
     * @param h   限定的时间
     */
    public static int eatBanana(int[] arr, int h) {
        int max = MaxGet.getMax(arr);
        for (int i = 0; i < max; i++) {
            int speed = arr[i];
            if (canEatFinish(arr, h, speed)) {
                return speed;
            }
        }
        return -1;
    }

    /**
     * @param arr 香蕉堆
     * @param h   限定的时间
     */
    public static int eatBananaV2(int[] arr, int h) {
        int max = MaxGet.getMax(arr);
        int left = 0, right = max;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canEatFinish(arr, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    /**
     * 判断是否可以吃完
     */
    public static boolean canEatFinish(int[] arr, int h, int speed) {
        int useTime = 0;
        for (int i = 0; i < arr.length; i++) {
            useTime += NumberUtils.divideUp(arr[i], speed);
        }
        return useTime <= h;
    }

}
