package com.jef.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 填充容器
 * @author Jef
 * @date 20180728
 */
public class FillingLists {
    public static void main(String[] args) {
        // 填充元素
        ArrayList<String> list = new ArrayList<>(Collections.nCopies(4,  "hello"));
        System.out.println(list);
        // 替换
        Collections.fill(list, "world");
        System.out.println(list);
    }
}
