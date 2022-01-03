package com.jef.algorithm.greedy;

import java.util.Arrays;

/**
 * 分糖果-贪心算法
 * 有 m 个糖果和 n 个孩⼦。我们现在要把糖果分给这些孩⼦吃，但是糖果少，孩⼦多（m < n），所以糖果只能分配给⼀部分孩⼦。每个糖果的⼤⼩不等，这 m 个糖果的⼤⼩分别是s1，s2，s3，……，sm。
 * 除此之外，每个孩⼦对糖果⼤⼩的需求也是不⼀样的，只有糖果的⼤⼩⼤于等于孩⼦的对糖果⼤⼩的需求的时候，孩⼦才得到满⾜。假设这 n 个孩⼦对糖果⼤⼩的需求分别是 g1，g2，g3，……，gn。那么如何分配糖果，
 * 能尽可能满⾜最多数量的⼦呢？
 *
 * @author Jef
 * @date 2022/1/3
 */
public class ShareCandy {

    /**
     * 获取能分配给⼩孩且符合条件的最多糖果数
     *
     * @param gList 需要糖果数组
     * @param sList 有的糖果数组
     * @return int 能分配给⼩孩且符合条件的最多糖果数
     * @author Jef
     * @date 2022/1/3
     */
    public static int dispatchCandy(int[] gList, int[] sList) {
        // ⼩孩对糖果的需求从⼩到⼤排列
        Arrays.sort(gList);
        // 糖果⼤⼩从⼩到⼤排列
        Arrays.sort(sList);
        int maximumCandyNum = 0;
        for (int i = 0; i < gList.length; i++) {
            for (int j = 0; j < sList.length; j++) {
                // 选择最接近⼩孩需求的糖果，以便让更⼤的糖果满⾜需求更⼤的⼩孩
                if (gList[i] <= sList[j]) {
                    maximumCandyNum++;
                    // 糖果被选中，将其置为-1，代表⽆效了
                    sList[j] = -1;
                    // 糖果已选中，跳出
                    break;
                }
            }
        }
        return maximumCandyNum;
    }

}