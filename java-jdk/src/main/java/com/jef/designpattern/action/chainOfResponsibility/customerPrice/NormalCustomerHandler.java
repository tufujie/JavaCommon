package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;

/**
 * 具体算法实现，为新客户或者是普通客户计算应报的价格
 *
 * @author Jef
 * @date 2019/2/13
 */
public class NormalCustomerHandler implements ICustomerHandler {

    @Override
    public double handleCustomer(Customer customer, ICustomerHandleChain customerHandleChain) {
        if ("normal".equals(customer.getType())) {
            System.out.println("对于新客户或者是普通客户，没有折扣");
            return customer.getGoodsPrice();
        }
        // 处理不了该回执就往下传递
        else {
            return customerHandleChain.handleCustomer(customer);
        }
    }
}