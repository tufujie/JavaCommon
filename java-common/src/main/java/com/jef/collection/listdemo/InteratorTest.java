package com.jef.collection.listdemo;

import java.util.*;

public class InteratorTest {
    public static void main(String[] args) {
        List<Integer> numList = new ArrayList<>();
        Collections.addAll(numList, 1 , 2, 3);
        for (Integer aNumList : numList) {
            System.out.println(aNumList);
        }
    }
}
