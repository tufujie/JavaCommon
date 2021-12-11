package com.jef.designpattern.action.strategy;

/**
 * 策略接口实现类，也就是具体的处理者
 * 具体算法实现，为大客户计算应报的价格
 * @author Jef
 * @date 2019/2/13
 */
public class LargeCustomerStratery implements ICustomerStrategy {

    @Override
    public double calcPrice(Customer customer) {
        System.out.println("对于大客户，统一折扣10%");
        return customer.getGoodsPrice() * (1 - 0.1);
//        return goodsPrice * 0.9;
    }
}