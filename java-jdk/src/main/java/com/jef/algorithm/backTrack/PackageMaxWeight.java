package com.jef.algorithm.backTrack;

import java.util.List;

/**
 * 01背包问题-回溯算法
 * 有⼀个背包，背包总的承载᯿量是 Wkg。现在我们有 n 个物品，每个物品的᯿量不等，并且不可分割。我们现在期望选择⼏件物品，装载到背包中。在不超过背包所能装载᯿量的前提下，如何
 * 让背包中物品的总᯿量最⼤？ 假设这 n 个物品的质量分别 3kg, 4kg, 6kg, 8kg，背包总的承载重量是 10kg。
 *
 * @author Jef
 * @date 2022/1/3
 */
public class PackageMaxWeight {

    /**
     * 结果集
     */
    Integer result = 0;

    /**
     * 遍历当前阶段的解
     *
     * @param selectedWeights  已选解集合
     * @param selectableWeight 可选的解集合
     */
    public void knapsack(List<Integer> selectedWeights, List<Integer> selectableWeight, Integer knapsackMaxWeight) {
        // 求已选物品的总᯿量
        int sumOfWeights = selectedWeights.stream().mapToInt(Integer::intValue).sum();
        if (sumOfWeights == knapsackMaxWeight) {
            result = Math.max(result, sumOfWeights);
            return;
        } else if (sumOfWeights > knapsackMaxWeight) {
            // 如果已选物品的总᯿量超过背包最⼤承受质量，则要把最后⼀个选择的物品移除，再求质量和
            selectedWeights.remove(selectedWeights.size() - 1);
            sumOfWeights = selectedWeights.stream().mapToInt(Integer::intValue).sum();
            result = Math.max(result, sumOfWeights);
            return;
        } else {
            result = Math.max(result, sumOfWeights);
        }
        // 遍历每个阶段的可选解集合
        for (int i = 0; i < selectableWeight.size(); i++) {
            Integer num = selectableWeight.get(i);
            // 去除不符合条件的解，减枝
            if (selectedWeights.contains(num)) {
                continue;
            }
            // 选择⼦节点的其中⼀个解
            selectedWeights.add(num);
            // 选完之后再进⾏ dfs
            knapsack(selectedWeights, selectableWeight, knapsackMaxWeight);
            // 「回溯」换个解再遍历
            selectedWeights.remove(num);
        }
    }
}