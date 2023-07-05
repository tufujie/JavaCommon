package com.jef.designpattern.structure.flyweight.one;

/**
 * 享元接口，通过这个接口享元可以接受并作用于外部状态
 * @author Jef
 * @date 2019/02/20
 */
public interface FlyWeight {
    /**
     * 判断出入的安全实体和权限，是否和享元对象内部状态匹配
     * @param secutiryEntity 安全实体
     * @param permit 权限
     * @return true表示匹配，false表示不匹配
     */
    boolean match(String secutiryEntity, String permit);
}
