package com.jef.jdk8;

/**
 * jdk8接口新增功能
 * @author Jef
 * @date 2019/3/14
 */
public interface InterfaceAdd {
    /**
     * 新添加了default方法
     */
    default String defaultFunction() {
        return "default方法";
    }

    /**
     * 新添加了static方法
     */
    static String staticFunction() {
        return "static方法";
    }

}