package com.jef.collection.listdemo;

import com.jef.constant.BasicConstant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 按照添加的顺序排序
 * @author Jef
 * @date 20180803
 */
public class ArrayListTest {
    public static void main(String[] args) {
        subListTest();
    }

    private static void baseDemo() {
        // 设置初始化大小
        ArrayList<Integer> arrayList = new ArrayList(5);

        arrayList.ensureCapacity(6);
        // 移除全部元素
        List<Integer> statusList = new ArrayList<>();
        statusList.add(1);
        statusList.add(2);
        System.out.println(statusList.size());
        statusList.clear();
        System.out.println(statusList.size());
        statusList.add(3);
        System.out.println(statusList.size());
    }

    /**
     * subList的错误用法和正确用法
     */
    private static void subListTest() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(BasicConstant.STR_ONE);
        arrayList.add(BasicConstant.STR_TWO);
        // 错误写法
        ArrayList<String> newArrayList = null;
        try {
            newArrayList = (ArrayList<String>) arrayList.subList(0, 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(newArrayList);
        // 正确写法
        List<String> newList = arrayList.subList(0, 1);
        System.out.println(newList);
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (str.equals(BasicConstant.STR_ONE)) {
                // 错误写法
//                 arrayList.remove(str);
                iterator.remove();
            }
        }
        System.out.println(arrayList);
    }
}
