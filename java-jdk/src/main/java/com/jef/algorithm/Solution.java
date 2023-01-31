package com.jef.algorithm;

/**
 * @author Jef
 * @date 2022/12/23
 */
class Solution {

    public boolean checkXMatrix(int[][] grid) {
        boolean flag = true;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (i == j || i + j == grid.length - 1) {
                    if (grid[i][j] == 0) {
                        flag = false;
                        break;
                    }
                } else if (grid[i][j] != 0) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        int[][] arr = {{5, 7, 0}, {0, 3, 1}, {0, 5, 0}};
        System.out.println(new Solution().checkXMatrix(arr));
        int[][] arr2 = {{2, 0, 0, 1}, {0, 3, 1, 0}, {0, 5, 2, 0}, {4, 0, 0, 2}};
        System.out.println(new Solution().checkXMatrix(arr2));
    }
}