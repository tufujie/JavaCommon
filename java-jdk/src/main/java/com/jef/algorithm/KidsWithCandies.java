package com.jef.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class KidsWithCandies {

    public static void main(String[] args) {
        System.out.println(new KidsWithCandies().kidsWithCandies(new int[]{2,3,5,1,3}, 3));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int candy : candies) {
            if (candy > max) {
                max = candy;
            }
        }
        List<Boolean> list = new ArrayList<>();
        for (int candy : candies) {
            list.add(candy + extraCandies >= max);
        }
        return list;
    }

}