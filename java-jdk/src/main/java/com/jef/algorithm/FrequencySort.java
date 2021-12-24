package com.jef.algorithm;

import java.util.Map;
import java.util.TreeMap;

/**
 * 根据字符出现频率排序
 *
 * @author Jef
 * @date 2021/7/3
 */
public class FrequencySort {

    public static void main(String[] args) {
        System.out.println(new FrequencySort().frequencySort("tree"));
        System.out.println(new FrequencySort().frequencySort("cccaaa"));
        System.out.println(new FrequencySort().frequencySort("Aabb"));
    }

    /**
     * 个人解法
     *
     * @author Jef
     * @date 2021/5/30
     */
    public String frequencySort(String s) {
        String[] stringArray = s.split("");
        // 先整理出每个字符对应的次数
        Map<String, Integer> stringIntegerMap = new TreeMap<>();
        for (String str : stringArray) {
            int count = 1;
            if (stringIntegerMap.containsKey(str)) {
                count = stringIntegerMap.get(str);
                count++;
            }
            stringIntegerMap.put(str, count);
        }
        // key和value置换位置
        Map<Integer, String> integerStringMap = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : stringIntegerMap.entrySet()) {
            String strValue = entry.getKey();
            if (integerStringMap.containsKey(entry.getValue())) {
                strValue += integerStringMap.get(entry.getValue());
            }
            integerStringMap.put(entry.getValue(), strValue);
        }
        String result = "";
        for (Map.Entry<Integer, String> entry : integerStringMap.entrySet()) {
            String[] keyArray = entry.getValue().split("");
            for (int i = 0; i < keyArray.length; i++) {
                for (int j = 0; j < entry.getKey(); j++) {
                    result = keyArray[i] + result;
                }
            }
        }
        return result;
    }

}