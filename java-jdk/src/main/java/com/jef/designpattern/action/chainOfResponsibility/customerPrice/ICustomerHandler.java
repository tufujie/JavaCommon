package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;

/**
 * 处理者接口
 *
 * @author Jef
 * @date 2020/7/21 0021
 */
public interface ICustomerHandler {

    double handleCustomer(Customer customer, ICustomerHandleChain customerHandleChain);
}
