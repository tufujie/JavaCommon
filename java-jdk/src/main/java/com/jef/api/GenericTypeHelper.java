package com.jef.api;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.core.GenericTypeResolver;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tufujie
 * @date 2023/9/22
 */
public class GenericTypeHelper {

    private static final ConcurrentHashMap<TypeArgumentTuple, Class<?>> CACHE = new ConcurrentHashMap<>();

    /**
     * 获取类型参数<br>
     * 例如：{@code GenericTypeHelper.getTypeArgument(MyClass.class, MyInterface.class)}<br>
     * 注意: 使用了不会主动回收的缓存, 请谨慎使用
     *
     * @param type      类型
     * @param targetIfc 目标接口
     * @return type实现targetIfc的类型参数
     */
    public static Class<?> getTypeArgument(Class<?> type, Class<?> targetIfc) {
        TypeArgumentTuple tuple = new TypeArgumentTuple(type, targetIfc);
        return CACHE.computeIfAbsent(tuple,
                typeArgumentTuple -> GenericTypeResolver.resolveTypeArgument(tuple.type, tuple.targetIfc));
    }

    private static class TypeArgumentTuple {
        private final Class<?> type;
        private final Class<?> targetIfc;

        public TypeArgumentTuple(Class<?> type, Class<?> targetIfc) {
            this.type = type;
            this.targetIfc = targetIfc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            TypeArgumentTuple that = (TypeArgumentTuple) o;

            return new EqualsBuilder().append(type, that.type).append(targetIfc, that.targetIfc).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(type).append(targetIfc).toHashCode();
        }

    }

}