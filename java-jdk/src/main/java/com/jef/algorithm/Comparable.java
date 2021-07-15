package com.jef.algorithm;

/**
 * @author Jef
 * @date 2020/4/5
 */
public interface Comparable<AnyType> {
    /**
     * 对比
     * @author Jef
     * @date 2020/4/5
     * @param other
     * @return int
     */
    int compareTo(AnyType other);
    /**
     * 对比
     * @author Jef
     * @date 2020/4/5
     * @param lhs 值1
     * @param rhs 值2
     * @return int
     */
    int compareTo(AnyType lhs, AnyType rhs);
}