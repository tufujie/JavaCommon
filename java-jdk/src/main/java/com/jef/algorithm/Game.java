package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class Game {

    public static void main(String[] args) {
        System.out.println(new Game().game(new int[]{1,2,3}, new int[]{1,2,3}));
        System.out.println(new Game().game(new int[]{2,2,3}, new int[]{3,2,1}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int game(int[] guess, int[] answer) {
        int totalRightNum = 0;
        for (int i = 0; i < answer.length; i++) {
            totalRightNum += (guess[i] == answer[i]) ? 1 : 0;
        }
        return totalRightNum;
    }

}