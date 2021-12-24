package com.jef.algorithm;

/**
 * 访问所有点的最小时间
 *
 * @author Jef
 * @date 2021/5/30
 */
public class MinTimeToVisitAllPoints {

    public static void main(String[] args) {
        System.out.println(new MinTimeToVisitAllPoints().minTimeToVisitAllPoints(new int[][]{{1,1},{3,4},{-1,0}}));
        System.out.println(new MinTimeToVisitAllPoints().minTimeToVisitAllPoints(new int[][]{{3,2},{-2,2}}));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int minTimeToVisitAllPoints(int[][] points) {
        int num = 0;
        for (int i = 0; i < points.length - 1; i++) {
            num += Math.max(Math.abs(points[i][0] - points[i + 1][0]), Math.abs(points[i][1] - points[i + 1][1]));
        }
        return num;
    }

}