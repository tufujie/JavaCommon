package com.jef.designpattern.action.command;

/**
 * 广东厨师
 *
 * @author Jef
 * @date 2021/12/11
 */
public class GuangDongCuisine implements ICussine {

    private ICook cook;

    public GuangDongCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCooking();
    }
}