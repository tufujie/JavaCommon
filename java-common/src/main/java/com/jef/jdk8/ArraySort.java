package com.jef.jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * Java排列字符串
 */
public class ArraySort {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        // JDK1.8之前,只需要给静态方法 Collections.sort 传入一个List对象以及
        // 一个比较器来按指定顺序排列。通常做法都是创建一个匿名的比较器对象然后将其传递给sort方法。
        Collections.sort(names);
        System.out.println("按照自然排序=" + names);
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println("按照自然排序的逆序排序=" + names);
        // JDK1.8，Java 8提供了更简洁的语法，lambda表达式，代码变得更短且更具有可读性
        Collections.sort(names, (String a, String b) -> {
            return a.compareTo(b);
        });
        System.out.println("使用Lambda表达式，按照自然排序=" + names);
        // 于函数体只有一行代码的，你可以去掉大括号{}以及return关键字，如下
        Collections.sort(names, (String a, String b) -> a.compareTo(b));
        System.out.println("使用Lambda表达式，按照自然排序，简化写法=" + names);
        // 实际上还可以写得更短
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println("使用Lambda表达式，按照自然排序，最短写法=" + names);
    }
}
