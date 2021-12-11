package com.jef.designpattern.action.command;

/**
 * 广东厨师
 *
 * @author Jef
 * @date 2021/12/11
 */
public class GuangDongCook implements ICook {


    @Override
    public void doCooking() {
        System.out.println("广东厨师，烹饪广东菜");
    }
}