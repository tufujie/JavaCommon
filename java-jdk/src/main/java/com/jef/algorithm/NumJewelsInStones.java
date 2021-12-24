package com.jef.algorithm;

/**
 * 宝石与石头
 *
 * @author Jef
 * @date 2021/5/30
 */
public class NumJewelsInStones {

    public static void main(String[] args) {
        System.out.println(new NumJewelsInStones().numJewelsInStones("aA", "aAAbbbb"));
        System.out.println(new NumJewelsInStones().numJewelsInStones("z", "ZZ"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int numJewelsInStones(String jewels, String stones) {
        String[] jewelsArray = jewels.split(""), stonesArray = stones.split("");
        int jewelNum = 0;
        for (String jewelTemp : jewelsArray) {
            for (String stoneTemp : stonesArray) {
                if (jewelTemp.equals(stoneTemp)) {
                    ++jewelNum;
                }
            }
        }
        return jewelNum;
    }

}