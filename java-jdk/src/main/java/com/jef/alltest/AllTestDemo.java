package com.jef.alltest;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jef.entity.User;
import com.jef.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2018/9/25 11:01
 */
public class AllTestDemo {
    public static void main(String[] args) throws Exception {
        BigDecimal a = new BigDecimal(8367.750);
        BigDecimal b = new BigDecimal(0.00);
        BigDecimal c = NumberUtils.subtract(a, b);
        System.out.println(c);
        System.out.println(NumberUtils.divide(c, b, 2));

    }


}
