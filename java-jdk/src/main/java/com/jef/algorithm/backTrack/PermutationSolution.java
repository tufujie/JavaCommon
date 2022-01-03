package com.jef.algorithm.backTrack;

import java.util.Arrays;
import java.util.List;

/**
 * 排列组合问题-回溯算法
 * 给定数字 1，2，3，求出 3 位不重复数字的全排列
 *
 * @author Jef
 * @date 2022/1/3
 */
public class PermutationSolution {

    /**
     * 遍历当前阶段的解
     *
     * @param selectedNums   已选解集合
     * @param selectableNums 可选的解集合
     */
    public static void permutation(List<Integer> selectedNums, List<Integer> selectableNums, List<String> result) {
        // 满⾜条件，加⼊结果集
        if (selectedNums.size() == selectableNums.size()) {
            result.add(Arrays.toString(selectedNums.toArray()));
            return;
        }
        // 遍历每个阶段的可选解集合
        for (int i = 0; i < selectableNums.size(); i++) {
            Integer num = selectableNums.get(i);
            // 去除不符合条件的解，减枝
            if (selectedNums.contains(num)) {
                continue;
            }
            // 选择当前阶段其中⼀个解
            selectedNums.add(num);
            // 选完之后再进⼊下个阶段遍历
            permutation(selectedNums, selectableNums, result);
            // 回溯,换⼀个解继续遍历
            selectedNums.remove(num);
        }
    }

}