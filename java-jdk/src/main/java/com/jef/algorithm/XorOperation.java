package com.jef.algorithm;

/**
 * 数组异或操作
 *
 * @author Jef
 * @date 2021/5/30
 */
public class XorOperation {

    public static void main(String[] args) {
        System.out.println(new XorOperation().xorOperation(5, 0));
        System.out.println(new XorOperation().xorOperation(4, 3));
        System.out.println(new XorOperation().xorOperation(1, 7));
        System.out.println(new XorOperation().xorOperation(10, 5));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @param n
     * @return int
     */
    public int xorOperation(int n, int start) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            result ^= (start + 2 * i);
        }
        return result;
    }

}