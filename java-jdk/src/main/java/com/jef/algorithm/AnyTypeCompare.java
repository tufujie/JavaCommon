package com.jef.algorithm;

import java.util.Comparator;
import java.util.List;

/**
 * @author Jef
 * @date 2020/4/5
 */
public class AnyTypeCompare {

    /**
     * 找出数组中的最大数
     * @author Jef
     * @date 2020/4/5
     * @param anyTypeList 任何类型的集合
     * @return AnyType
     */
    public static <AnyType> AnyType findMax(List<AnyType> anyTypeList, Comparator<? super AnyType> cmp) {
        int maxIndex = 0;
        for (int i = 0; i < anyTypeList.size(); i++) {
            if (cmp.compare(anyTypeList.get(i), anyTypeList.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return anyTypeList.get(maxIndex);
    }

}
