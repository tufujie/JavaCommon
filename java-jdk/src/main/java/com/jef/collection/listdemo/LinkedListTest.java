package com.jef.collection.listdemo;

import org.springframework.util.CollectionUtils;
import java.util.Collections;
import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> nums = new LinkedList<>();
        // 添加首元素，等效于push()
        nums.addFirst(1);
        // 添加尾元素，等效于add()
        nums.addLast(2);

        // 移除首元素并返回，1，等效于remove()，pop()
        System.out.println(nums.removeFirst());
        // 再移除首元素并返回，2，此时列表为空，推荐使用
        System.out.println(nums.poll());
        // 列表为空时，再次移除，返回null
        System.out.println(nums.poll());
        // 元素添加回来
        Collections.addAll(nums, 1, 2);
        // 移除尾元素并返回，2
        System.out.println(nums.removeLast());

        // 修改元素，下标从0开始
        System.out.println(nums);
        nums.set(0, 3);
        System.out.println(nums);

        // 获取首元素
        System.out.println(nums.peek());
        System.out.println(nums.getFirst());
        System.out.println(nums.element());
        // 获取尾元素
        System.out.println(nums.getLast());
        // 判断集合是否为空
        System.out.println(CollectionUtils.isEmpty(nums));
        System.out.println(nums.isEmpty());
    }
}
