package com.jef.algorithm.sort;

import com.jef.util.ListSortUtil;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 数字排序
 *
 * @author Jef
 * @date 2021/4/13
 */
public class ListSortTest {

    @Test
    public void testAddAndSort() {
        List<Integer> listOne = new ArrayList<>();
        listOne.add(2);
        listOne.add(4);
        List<Integer> listTwo = new ArrayList<>();
        listTwo.add(1);
        listTwo.add(3);
        List<Integer> resultList = ListSortUtil.addAndSort(listOne, listTwo);
        System.out.println(resultList);
        int[] resultListV2 = ListSortUtil.addAndSortV2(listOne, listTwo);
        System.out.println(resultListV2);
        Set<Integer> resultSetV3 = ListSortUtil.addAndSortV3(listOne, listTwo);
        System.out.println(resultSetV3);
    }


}