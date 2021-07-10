package com.jef.jdk8;
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);
    int num = 1;

    static void main(String[] args) {
        Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
    }
}
