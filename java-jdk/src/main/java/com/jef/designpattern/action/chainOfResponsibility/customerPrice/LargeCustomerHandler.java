package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;

/**
 * 策略接口实现类，也就是具体的处理者
 * 具体算法实现，为大客户计算应报的价格
 *
 * @author Jef
 * @date 2019/2/13
 */
public class LargeCustomerHandler implements ICustomerHandler {

    @Override
    public double handleCustomer(Customer customer, ICustomerHandleChain customerHandleChain) {
        if ("large".equals(customer.getType())) {
            System.out.println("对于大客户，统一折扣10%");
            return customer.getGoodsPrice() * (1 - 0.1);
        }
        // 处理不了该回执就往下传递
        else {
            return customerHandleChain.handleCustomer(customer);
        }
    }
}