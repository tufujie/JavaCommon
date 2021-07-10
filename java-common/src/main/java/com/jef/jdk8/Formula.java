package com.jef.jdk8;

public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

    static void main(String[] args) {
    }
}
