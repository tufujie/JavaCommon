package com.jef.designpattern.action.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 小二
 *
 * @author Jef
 * @date 2021/12/11
 */
public class XiaoEr {

    private List<ICussine> cussineList = new ArrayList<ICussine>();

    /**
     * 点单菜品
     *
     * @param cussine 菜品vo
     * @author Jef
     * @date 2021/12/11
     */
    public void order(ICussine cussine) {
        cussineList.add(cussine);
    }

    /**
     * 下单菜品
     *
     * @author Jef
     * @date 2021/12/11
     */
    public void placeOrder() {
        for (ICussine cussine : cussineList) {
            cussine.cook();
        }
        cussineList.clear();
    }

}