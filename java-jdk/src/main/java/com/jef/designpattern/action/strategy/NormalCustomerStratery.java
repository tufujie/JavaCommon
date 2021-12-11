package com.jef.designpattern.action.strategy;

/**
 * 具体算法实现，为新客户或者是普通客户计算应报的价格
 * @author Jef
 * @date 2019/2/13
 */
public class NormalCustomerStratery implements ICustomerStrategy {

    @Override
    public double calcPrice(Customer customer) {
        System.out.println("对于新客户或者是普通客户，没有折扣");
        return customer.getGoodsPrice();
    }
}