package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;

/**
 * 责任链接口
 *
 * @author Jef
 * @date 2020/7/21 0021
 */
public interface ICustomerHandleChain {

    double handleCustomer(Customer customer);

}
