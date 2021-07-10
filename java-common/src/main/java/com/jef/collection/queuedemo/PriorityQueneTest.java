package com.jef.collection.queuedemo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQueneTest {
    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        PriorityQueue quene = new PriorityQueue(ints);
        System.out.println("升序");
        System.out.println(quene);

        quene = new PriorityQueue(ints.size(), Collections.reverseOrder());
        quene.addAll(ints);
        System.out.println("降序");
        System.out.println(quene);
    }
}
