package com.jef.jdk8;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public interface ISetListData<T, R> {
    void setData(List<T> data, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet);

    void setData(List<T> data, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet, R defaultData);
}