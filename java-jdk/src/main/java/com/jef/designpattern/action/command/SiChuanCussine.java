package com.jef.designpattern.action.command;

/**
 * 四川厨师
 *
 * @author Jef
 * @date 2021/12/11
 */
public class SiChuanCussine implements ICussine {

    private ICook cook;

    public SiChuanCussine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}