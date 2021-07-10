package com.jef.algorithm;

import com.jef.util.IntegerUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author Jef
 * @date 2020/4/6
 */
public class MaxSubSumTest {

    @Test
    public void tesstMaxSubSum1() {
        System.out.println(MaxSubSum.maxSubSum1(IntegerUtil.getTestIntegerList()));
    }

    @Test
    public void tesstMaxSubSum2() {
        System.out.println(MaxSubSum.maxSubSum2(IntegerUtil.getTestIntegerListV2()));
    }

    @Test
    public void tesstMaxSubSum3() {
        System.out.println(MaxSubSum.maxSubSum3(IntegerUtil.getTestIntegerListV2()));
    }

    @Test
    public void tesstMaxSubSum3V2() {
        System.out.println(MaxSubSum.maxSubSum3(Arrays.asList(3, -1, 2)));
    }

    @Test
    public void tesstMaxSubSum4() {
        System.out.println(MaxSubSum.maxSubSum4(IntegerUtil.getTestIntegerListV2()));
    }

    @Test
    public void getTimingInfo() {
        int i = 5;
        while (i-- > 1) {
            MaxSubSum.getTimingInfo(100, i);
        }
    }

}