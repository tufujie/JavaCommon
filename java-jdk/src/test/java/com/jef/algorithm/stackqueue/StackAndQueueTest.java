package com.jef.algorithm.stackqueue;

import com.jef.util.algorithm.stackQueue.StackAndQueue;

import org.junit.jupiter.api.Test;

/**
 * @author Jef
 * @date 2023/4/2
 */
public class StackAndQueueTest {
    @Test
    public void testPushPop() {
        StackAndQueue.push(1);
        StackAndQueue.push(2);
        StackAndQueue.push(3);
        System.out.println(StackAndQueue.pop());
        System.out.println(StackAndQueue.pop());
        System.out.println(StackAndQueue.pop());
    }

    @Test
    public void testIsPopOrder() {
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println(StackAndQueue.isPopOrder(arr1, new int[]{4, 5, 3, 2, 1}));
        System.out.println(StackAndQueue.isPopOrder(arr1, new int[]{4, 3, 5, 1, 2}));
    }
}