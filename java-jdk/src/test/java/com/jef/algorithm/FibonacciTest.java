package com.jef.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2023/4/2
 */
public class FibonacciTest {

    @Test
    public void testFibonacci() {
        System.out.println(Fibonacci.fibonacci(5));
        System.out.println(Fibonacci.fibonacci2(5));
    }

}