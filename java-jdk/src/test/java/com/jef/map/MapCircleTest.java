package com.jef.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author tufujie
 * @date 2023/8/18
 */
public class MapCircleTest {

    @Test
    public void testInCircle() {
        float a = 1.1f;
        float b = 1.2f;
        float c = 1.1f;
        float d = 1.2f;
        boolean result = MapCircle.inCircle(a, b, 50, c, d);
        Assertions.assertTrue(result);
        result = MapCircle.inCircle(100, 100, 50, c, d);
        Assertions.assertFalse(result);
    }

}