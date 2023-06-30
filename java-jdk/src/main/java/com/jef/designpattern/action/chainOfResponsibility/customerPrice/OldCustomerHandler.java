package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;

/**
 * 具体算法实现，为老客户计算应报的价格
 *
 * @author Jef
 * @date 2019/2/13
 */
public class OldCustomerHandler implements ICustomerHandler {

    @Override
    public double handleCustomer(Customer customer, ICustomerHandleChain customerHandleChain) {
        if ("old".equals(customer.getType())) {
            System.out.println("对于老客户，统一折扣5%");
            return customer.getGoodsPrice() * (1 - 0.05);
        }
        // 处理不了该回执就往下传递
        else {
            return customerHandleChain.handleCustomer(customer);
        }
    }
}