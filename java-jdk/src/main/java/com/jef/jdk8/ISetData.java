package com.jef.jdk8;

import java.util.function.BiConsumer;
import java.util.function.Function;

public interface ISetData<T, R> {
    void setData(T data, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet);

    void setData(T data, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet, R defaultData);
}
