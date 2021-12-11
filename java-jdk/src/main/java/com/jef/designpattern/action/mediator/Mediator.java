package com.jef.designpattern.action.mediator;

/**
 * 中介模式的中介者接口
 * @author Jef
 * @date 20180902
 */
public interface Mediator {

    /**
     * 同事类变化操作
     * @param colleague
     */
    void changeed(BaseColleague colleague);
}
