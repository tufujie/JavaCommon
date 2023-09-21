package com.jef.util;

import org.junit.Test;

/**
 * @author tufujie
 * @date 2023/9/19
 */
public class NumberUtilsTest {

    @Test
    public void testGetPercent() {
        System.out.println(NumberUtils.getPercent(2.45, 4));
    }

    @Test
    public void testGetRateValue() {
        System.out.println(NumberUtils.getRateValue(1000, 2.45, 4));
    }

}