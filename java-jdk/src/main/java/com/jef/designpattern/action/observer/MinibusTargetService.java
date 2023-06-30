package com.jef.designpattern.action.observer;

/**
 * 模拟摇号系统
 *
 * @author Jef
 * @date 2021/12/11
 */
public class MinibusTargetService {

    /**
     * 获取摇号结果
     *
     * @param uId 用户编码
     * @author Jef
     * @date 2021/12/11
     */
    public String getLotteryResult(String uId) {
        return Math.abs(uId.hashCode()) % 2 == 0 ? "恭喜您，编码".concat(uId).concat("在本次摇号中中签") : "很遗憾，编码".concat(uId).concat("在本次摇号中未中签或摇号资格已过期");
    }

}