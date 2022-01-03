package com.jef.algorithm.backTrack;

import com.jef.util.PrintUtil;

/**
 * N皇后问题-回溯算法
 * 8x8 的棋盘，希望往⾥放 8 个棋⼦（皇后），每个棋⼦所在的⾏、列、对⻆线都不能有另⼀个棋⼦
 *
 * @author Jef
 * @date 2022/1/3
 */
public class NQueen {

    /**
     * @param selectedColumns 已选解集合,下标表示⾏,值表示queen存储在哪⼀列
     * @param row             可选的空间解,第 n ⾏可选
     */
    public static void queenSettle(int[] selectedColumns, int row, int n) {
        // 终⽌条件
        if (row > n - 1) {
            // 说明前 N ⾏都已经都选完皇后了，
            printQueens(selectedColumns, n);
            return;
        }

        for (int i = 0; i < n; i++) {
            // 剔除不合法的格⼦
            if (!isValid(row, i, selectedColumns)) {
                continue;
            }
            // 选择⼦节点（当前⾏）其中⼀个解
            selectedColumns[row] = i;
            // 选完之后再进⼊下个阶段的（下⼀⾏）遍历
            queenSettle(selectedColumns, row + 1, n);
            // 回溯，换⼀个解继续 dfs，回溯时要把回溯节点的解移除
            selectedColumns[row] = -1;
        }

    }

    /**
     * 判断相应的格⼦放置皇后是否OK
     *
     * @param row
     * @param column
     * @param selectedColumns
     * @return
     */
    private static boolean isValid(int row, int column, int[] selectedColumns) {
        // 判断row⾏column列放置是否合适
        int leftup = column - 1, rightup = column + 1;
        // 逐⾏往上考察每⼀⾏
        for (int i = row - 1; i >= 0; --i) {
            // 第i⾏的column列有棋⼦吗？
            if (selectedColumns[i] == column) return false;
            // 考察左上对⻆线：第i⾏leftup列有棋⼦吗？
            if (leftup >= 0) {
                if (selectedColumns[i] == leftup) return false;
            }
            // 考察右上对⻆线：第i⾏rightup列有棋⼦吗？
            if (rightup < 8) {
                if (selectedColumns[i] == rightup) return false;
            }
            --leftup;
            ++rightup;
        }
        return true;
    }

    /**
     * 打印出⼀个⼆维矩阵
     *
     * @param result 皇后所在的列数组
     * @author Jef
     * @date 2022/1/3
     */
    private static void printQueens(int[] result, int n) {
        for (int row = 0; row < n; ++row) {
            for (int column = 0; column < n; ++column) {
                if (result[row] == column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        PrintUtil.printSplitLine();
    }
}