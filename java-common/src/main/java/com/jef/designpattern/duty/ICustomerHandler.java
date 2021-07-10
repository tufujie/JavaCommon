package com.jef.designpattern.duty;

import com.jef.designpattern.strategy.Customer;

/**
 * 处理者接口
 * @author Jef
 * @date 2020/7/21 0021
 */
public interface ICustomerHandler {

    double handleCustomer(Customer customer, ICustomerHandleChain customerHandleChain);
}
