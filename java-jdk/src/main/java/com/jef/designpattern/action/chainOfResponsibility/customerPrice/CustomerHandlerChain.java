package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;

import java.util.List;

/**
 * 责任链接口实现类
 *
 * @author Jef
 * @date 2020/7/21 0021
 */
public class CustomerHandlerChain implements ICustomerHandleChain {
    // 记录当前处理者位置
    private int index = 0;
    // 处理者集合
    private static List<ICustomerHandler> customerHandlerList;

    static {
        // 从容器中获取处理器对象
        customerHandlerList = CustomerHandlerContainer.getCustomerHandlerList();
    }

    @Override
    public double handleCustomer(Customer customer) {
        if (customerHandlerList != null && customerHandlerList.size() > 0) {
            for (int i = 0; i < customerHandlerList.size(); i++) {
                if (index >= customerHandlerList.size()) {
                    index = 0;
                }
                ICustomerHandler customerHandler = customerHandlerList.get(index++);
                return customerHandler.handleCustomer(customer, this);
            }
        }
        return 0;
    }
}