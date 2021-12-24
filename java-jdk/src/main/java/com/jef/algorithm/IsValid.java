package com.jef.algorithm;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class IsValid {

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("[()](())"));
        System.out.println(new IsValid().isValid("[({(())}[()])]"));
        System.out.println(new IsValid().isValid("[([]])"));
        System.out.println(new IsValid().isValid("[[[[[[[[[[[[[[[[[[["));
        System.out.println(new IsValid().isValid("["));
        System.out.println(new IsValid().isValid("[({(())}[()])]"));
        System.out.println(new IsValid().isValid("[({])}"));
        System.out.println(new IsValid().isValid("()"));
        System.out.println(new IsValid().isValid("()[]{}"));
        System.out.println(new IsValid().isValid("(]"));
        System.out.println(new IsValid().isValid("([)]"));
        System.out.println(new IsValid().isValid("{[]}"));
    }

    /**
     * 个人解法
     * 还存在bug
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public boolean isValid(String s) {
        while (s.contains("{}") || s.contains("[]") || s.contains("()")) {
            s = s.replace("{}", "");
            s = s.replace("[]", "");
            s = s.replace("()", "");
        }
        return s.length() == 0;
    }

}