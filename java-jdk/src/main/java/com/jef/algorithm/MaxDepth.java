package com.jef.algorithm;

/**
 * 括号的最大嵌套深度
 *
 * @author Jef
 * @date 2021/5/30
 */
public class MaxDepth {

    public static void main(String[] args) {
        System.out.println(new MaxDepth().maxDepth("(1+(2*3)+((8)/4))+1"));
        System.out.println(new MaxDepth().maxDepth("(1)+((2))+(((3)))"));
        System.out.println(new MaxDepth().maxDepth("1+(2*3)/(2-1)"));
        System.out.println(new MaxDepth().maxDepth("1"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int maxDepth(String s) {
        // 左括号没有匹配右括号的个数
        int leftNotPickNum = 0;
        // 最大深度
        int max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                // 深度加1
                leftNotPickNum++;
                // 更新最大值
                max = Math.max(leftNotPickNum, max);
            } else if (c == ')') {
                // 没有匹配到的个数减1
                leftNotPickNum--;
            }
        }
        return max;
    }

}