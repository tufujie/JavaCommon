package com.jef.algorithm.stackqueue;

import com.jef.util.Stack;
import com.jef.util.algorithm.stackQueue.StackAndQueue;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

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

    @Test
    public void testQueue() {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        queue.add("1");
        queue.add("2");
        queue.add("3");
        // 获取对头：1
        System.out.println("peek()=" + queue.peek());
        // 获取对头：1，并移除
        System.out.println("poll()=" + queue.poll());
        // 重新获取对头：2
        System.out.println("peek()=" + queue.peek());
        // 队尾添加元素
        queue.offer("4");
        // 重新获取对头：2
        System.out.println("peek()=" + queue.peek());
    }

    @Test
    public void testStack() {
        Stack<String> stack = new Stack<String>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        // 获取栈顶：3
        System.out.println("peek()=" + stack.peek());
        // 获取栈顶：3，并移除
        System.out.println("pop()=" + stack.pop());
        // 重新获取栈顶：2
        System.out.println("peek()=" + stack.peek());
    }

}