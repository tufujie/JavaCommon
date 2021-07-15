package com.jef.writePractice;

public class Square {
    long width;
    public Square(long width) {
        this.width = width;
    }

    public static void main(String[] args) {
        Square a, b, c;
        a = new Square(42L);
        b = new Square(42L);
        c = b;
        // 下面用于判断哪些是true
        System.out.println(a == b);  // false
        System.out.println(a.equals(b)); // false
        System.out.println(b.equals(c));
        System.out.println("------------");
        String d = "hello";
        String e = "hello";
        System.out.println(d.equals(e));
        System.out.println("------------");
        int f = 1;
        int g = 1;
        System.out.println(f == g);
    }

}
