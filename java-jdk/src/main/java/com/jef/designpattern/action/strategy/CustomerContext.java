package com.jef.designpattern.action.strategy;

/**
 * 策略上下文类(策略接口的持有者)
 * 价格管理，主要完成计算向客户所报价格的功能
 * @author Jef
 * @date 2019/2/13
 */
public class CustomerContext {
    /**
     * 持有一个具体的策略对象
     */
    private ICustomerStrategy customerStrategy = null;

    /**
     * 构造方法，传入一个具体的策略对象
     * @author Jef
     * @date 2019/2/14
     * @param customerStrategy
     */
    public CustomerContext(ICustomerStrategy customerStrategy) {
        this.customerStrategy = customerStrategy;
    }

    /**
     * 报价，计算对客户的报价
     * @author Jef
     * @date 2019/2/13
     * @param goodsPrice 商品销售原价
     * @return 计算出来的，应该给客户报的价格
     */
    public double handlePrice(Customer customer) {
        return customerStrategy.calcPrice(customer);
    }

}