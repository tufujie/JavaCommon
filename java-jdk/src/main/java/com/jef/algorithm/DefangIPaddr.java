package com.jef.algorithm;

/**
 *  IP 地址无效化
 *
 * @author Jef
 * @date 2021/5/30
 */
public class DefangIPaddr {

    public static void main(String[] args) {
        System.out.println(new DefangIPaddr().defangIPaddr("1.1.1.1"));
        System.out.println(new DefangIPaddr().defangIPaddr("255.100.50.0"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public String defangIPaddr(String address) {
        return address.replace(".", "[.]");
    }

}