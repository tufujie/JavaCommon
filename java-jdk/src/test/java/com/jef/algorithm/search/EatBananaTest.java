package com.jef.algorithm.search;

import org.junit.jupiter.api.Test;

public class EatBananaTest {

    @Test
    public void testEatBanana() {
        int[] arr = {1, 2, 3, 4};
        System.out.println(EatBanana.eatBanana(arr, 4));
        System.out.println(EatBanana.eatBanana(arr, 5));
    }

    @Test
    public void testEatBananaV2() {
        int[] arr = {1, 2, 3, 4};
        System.out.println(EatBanana.eatBananaV2(arr, 4));
        System.out.println(EatBanana.eatBananaV2(arr, 5));
    }
}
