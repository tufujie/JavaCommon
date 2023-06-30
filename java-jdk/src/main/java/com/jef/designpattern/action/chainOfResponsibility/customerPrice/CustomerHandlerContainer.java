package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import java.util.ArrayList;
import java.util.List;

/**
 * 责任链处理者容器(如果采用spring,则可以通过依赖注入的方式获取到IReceiptHandler的子类对象)
 *
 * @author Jef
 * @date 2020/7/21 0021
 */
public class CustomerHandlerContainer {
    private CustomerHandlerContainer() {
    }

    public static List<ICustomerHandler> getCustomerHandlerList() {
        List<ICustomerHandler> customerHandlerList = new ArrayList<>();
        // 任意添加顺序
        customerHandlerList.add(new OldCustomerHandler());
        customerHandlerList.add(new LargeCustomerHandler());
        customerHandlerList.add(new NormalCustomerHandler());
        return customerHandlerList;
    }
}