package com.jef.jdk8;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author Administrator
 * @date 2022/12/12
 */
public abstract class BaseDataSetGetFromCache<T, R> implements ISetListData<T, R>, ISetData<T, R> {
    private final Map<Long, R> localMap = new HashMap();

    public BaseDataSetGetFromCache() {
    }

    public BaseDataSetGetFromCache(Map<String, R> localMap) {
    }

    public void setData(List<T> data, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet) {
        this.setData(data, keyFGet, keyFSet, null);
    }

    public void setData(List<T> data, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet, R defaultData) {
        if (data != null) {
            Iterator var5 = data.iterator();
            while (var5.hasNext()) {
                T tempVo = (T) var5.next();
                this.setData(tempVo, keyFGet, keyFSet, defaultData);
            }

        }
    }

    public void setData(T tempVo, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet) {
        this.setData(tempVo, keyFGet, keyFSet, null);
    }

    public void setData(T tempVo, Function<T, Long> keyFGet, BiConsumer<T, R> keyFSet, R defaultData) {
        if (tempVo != null) {
            Long key = keyFGet.apply(tempVo);
            if (key != null) {
                if (this.localMap.containsKey(key)) {
                    if (this.localMap.get(key) == null) {
                        return;
                    }

                    keyFSet.accept(tempVo, this.localMap.get(key));
                } else {
                    R r = this.getDataFormCache(key);
                    if (!this.isNullData(r)) {
                        keyFSet.accept(tempVo, r);
                        this.localMap.put(key, r);
                    } else if (defaultData != null) {
                        this.localMap.put(key, defaultData);
                        keyFSet.accept(tempVo, defaultData);
                    } else {
                        this.localMap.put(key, null);
                    }
                }

            }
        }
    }

    protected abstract boolean isNullData(R r);

    protected abstract R getDataFormCache(Long key);
}