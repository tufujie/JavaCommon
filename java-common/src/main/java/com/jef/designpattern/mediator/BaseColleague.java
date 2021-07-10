package com.jef.designpattern.mediator;

/**
 * 同事抽象类，抽出公共方法
 * @author Jef
 * @date 20180902
 */
public abstract class BaseColleague {
    private Mediator mediator;
    public BaseColleague(Mediator mediator) {
        this.mediator = mediator;
    }
    public Mediator getMediator() {
        return mediator;
    }
}
