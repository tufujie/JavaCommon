package com.jef.designpattern.action.strategy;

/**
 * 具体算法实现，为老客户计算应报的价格
 * @author Jef
 * @date 2019/2/13
 */
public class OldCustomerStratery implements ICustomerStrategy {

    @Override
    public double calcPrice(Customer customer) {
        System.out.println("对于老客户，统一折扣5%");
        return customer.getGoodsPrice() * (1 - 0.05);
//        return goodsPrice * 0.95;
    }
}