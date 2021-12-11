package com.jef.designpattern.action.strategy;

/**
 * 策略接口
 * 策略，定义计算报价算法的接口
 * @author Jef
 * @date 2019/2/13
 */
public interface ICustomerStrategy {

    /**
     * 计算应报的价格
     * @author Jef
     * @date 2019/2/13
     * @return double 计算出来的，应该给客户报的价格
     */
    double calcPrice(Customer customer);
}
