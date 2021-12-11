package com.jef.designpattern.action.strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户价格创造器
 * @author Jef
 * @date 2020/7/21 0021
 */
public class CustomerBuilder {
    public static List<Customer> generatePriceList() {
        // 直接模拟一堆客户类型对象
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("large", 1000));
        customerList.add(new Customer("old", 1000));
        customerList.add(new Customer("normal", 1000));
        //......
        return customerList;
    }
}