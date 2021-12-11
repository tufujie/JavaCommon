package com.jef.designpattern.action.command;

/**
 * 四川厨师
 *
 * @author Jef
 * @date 2021/12/11
 */
public class SiChuanCook implements ICook {

    @Override
    public void doCooking() {
        System.out.println("四川厨师，烹饪四川菜");
    }
}