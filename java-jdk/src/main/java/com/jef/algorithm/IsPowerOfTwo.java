package com.jef.algorithm;

/**
 * 2 的幂
 * 给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。  如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。
 *
 * @author Jef
 * @date 2021/5/30
 */
public class IsPowerOfTwo {

    public static void main(String[] args) {
        System.out.println(new IsPowerOfTwo().isPowerOfTwo(16777217));
        System.out.println(new IsPowerOfTwo().isPowerOfTwo(1));
        System.out.println(new IsPowerOfTwo().isPowerOfTwo(16));
        System.out.println(new IsPowerOfTwo().isPowerOfTwo(3));
        System.out.println(new IsPowerOfTwo().isPowerOfTwo(4));
        System.out.println(new IsPowerOfTwo().isPowerOfTwo(5));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param n
     * @return boolean
     */
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        } else if (n == 1) {
            return true;
        }
        while (n > 1) {
            double tempN = (double)n / 2;
            int tempNInt = (int) tempN;
            if (tempN != tempNInt) {
                return false;
            }
            n = n / 2;
        }
        return true;
    }

}