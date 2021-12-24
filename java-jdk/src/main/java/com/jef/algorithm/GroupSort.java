package com.jef.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 排列组合
 * @date 2021/6/2
 */
public class GroupSort {
    /**
     * 排列的内容
     */
    private static ArrayList<ArrayList<String>> stringList;

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4};
        System.out.println("组合开始");
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> printlist = new ArrayList<>();
        Arrays.sort(arr);
        group(arr, list, printlist, 0);
        for (ArrayList<Integer> integerListTemp : printlist) {
            System.out.println(integerListTemp);
        }
        System.out.println("组合结束");
        System.out.println("排列开始");
        dfs(arr, "");
        for (List<String> stringListTemp : stringList) {
            System.out.println(stringListTemp);
        }
        System.out.println("排列开始");
        System.out.println("排列组合开始");
        ArrayList<ArrayList<String>> stringList = groupAndDfs(arr);
        for (List<String> stringListTemp : stringList) {
            System.out.println(stringListTemp);
        }
        System.out.println("排列组合结束");
    }

    /**
     * 排列组合
     * @author Jef
     * @date 2021/6/2
     * @param arr
     * @return java.util.List<java.lang.String>
     */
    public static ArrayList<ArrayList<String>> groupAndDfs(Integer[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<ArrayList<Integer>> printlist = new ArrayList<>();
        Arrays.sort(arr);
        group(arr, list, printlist, 0);
        stringList = new ArrayList<>();
        for (ArrayList<Integer> tem : printlist) {
            dfsByList(tem, "");
        }
        return stringList;
    }

    /**
     * 数组排列
     * @param candidate
     * @param prefix
     */
    public static void dfs(Integer[] candidate, String prefix) {
        stringList = new ArrayList<>();
        dfsByList(new ArrayList<Integer>(Arrays.asList(candidate)), prefix);
    }

    /**
     * 集合排列
     * @author Jef
     * @date 2021/6/2
     * @param candidate
     * @param prefix
     * @return void
     */
    public static void dfsByList(List<Integer> candidate, String prefix) {
        if (prefix.length() != 0 && candidate.size() == 0) {
            stringList.add(new ArrayList<>(Arrays.asList(prefix.split(""))));
        }
        for (int i = 0; i < candidate.size(); i++) {
            List<Integer> temp = new LinkedList<Integer>(candidate);
            int item = (int) temp.remove(i);  // 取出被删除的元素，这个元素当作一个组合用掉了
            dfsByList(temp, prefix + item);
        }
    }

    /**
     * 数组组合
     * @param arr
     * @param list
     * @param printlist
     * @param index
     * @return
     */
    public static ArrayList<ArrayList<Integer>> group(Integer[] arr, ArrayList<Integer> list, ArrayList<ArrayList<Integer>> printlist, int index) {
        if (list != null && !list.isEmpty()) {
            printlist.add(new ArrayList<>(list));
        }
        if (list.size() == arr.length) {
            return printlist;
        }
        for (int i = index; i < arr.length; i++) {
            list.add(arr[i]);
            group(arr, list, printlist, i + 1);
            list.remove(list.size() - 1);
        }
        return printlist;
    }

}