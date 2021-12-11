package com.jef.designpattern.action.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 * 客户类型工厂类
 * @author Jef
 * @date 2020/7/21 0021
 */
public class CustomerTypeHandleFactory {
    private static Map<String, ICustomerStrategy> customerStrategyMap;

    /**
     * 根据客户类型获取定价方式1
     * @param customerType
     * @return
     */
    public static ICustomerStrategy getCustomerStrategy(String customerType){
        ICustomerStrategy customerStrategy = null;
        if ("large".equals(customerType)) {
            customerStrategy = new LargeCustomerStratery();
        } else if ("old".equals(customerType)) {
            customerStrategy = new OldCustomerStratery();
        } else if ("normal".equals(customerType)) {
            customerStrategy = new NormalCustomerStratery();
        }
        return customerStrategy;
    }

    /**
     * 由于我们的目的是消除if-else，那么这里需要将ReceiptHandleStrategyFactory策略工厂进行改造下，
     * 采用字典的方式存放我的策略，而Map具备key-value结构，采用Map是个不错选择。
     * 经过对策略模式+简单工厂方案的改造，我们已经消除了if-else的结构，每当新来了一种回执，只需要添加新的回执处理策略，并修改ReceiptHandleStrategyFactory中的Map集合。
     * 如果要使得程序符合开闭原则，则需要调整ReceiptHandleStrategyFactory中处理策略的获取方式，通过反射的方式，获取指定包下的所有IReceiptHandleStrategy实现类，然后放到字典Map中去。
     */
    public CustomerTypeHandleFactory() {
        customerStrategyMap = new HashMap<>();
        customerStrategyMap.put("large", new LargeCustomerStratery());
        customerStrategyMap.put("old", new OldCustomerStratery());
        customerStrategyMap.put("normal", new NormalCustomerStratery());
    }

    /**
     * 根据客户类型获取定价方式2
     * @param customerType
     * @return
     */
    public ICustomerStrategy getCustomerHandleStrategy(String customerType){
        return customerStrategyMap.get(customerType);
    }
}