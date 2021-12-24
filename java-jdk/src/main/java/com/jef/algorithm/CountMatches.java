package com.jef.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找出所有子集的异或总和再求和
 *
 * @author Jef
 * @date 2021/5/30
 */
public class CountMatches {

    public static void main(String[] args) {
        List<List<String>> items = new ArrayList<>();
        items.add(new ArrayList<>(Arrays.asList(new String[]{"phone","blue","pixel"})));
        items.add(new ArrayList<>(Arrays.asList(new String[]{"computer","silver","lenovo"})));
        items.add(new ArrayList<>(Arrays.asList(new String[]{"phone","gold","iphone"})));
        System.out.println(new CountMatches().countMatches(items, "color", "silver"));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int pickNum = 0;
        for (List<String> item : items) {
            String compareStr;
            if ("type".equals(ruleKey)) {
                compareStr = item.get(0);
            } else if ("color".equals(ruleKey)) {
                compareStr = item.get(1);
            } else {
                compareStr = item.get(2);
            }
            pickNum += ruleValue.equals(compareStr) ? 1 : 0;
        }
        return pickNum;
    }

}