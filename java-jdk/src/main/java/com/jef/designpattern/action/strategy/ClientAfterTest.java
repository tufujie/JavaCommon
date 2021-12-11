package com.jef.designpattern.action.strategy;

import org.junit.Test;

import java.util.List;

/**
 * 策略模式客户端
 * 策略模式+Map字典
 * 我们知道， 策略模式的目的是封装一系列的算法，它们具有共性，可以相互替换，也就是说让算法独立于使用它的客户端而独立变化，客户端仅仅依赖于策略接口 。
 * 在上述场景中，我们可以把if-else分支的业务逻辑抽取为各种策略，但是不可避免的是依然需要客户端写一些if-else进行策略选择的逻辑，我们可以将这段逻辑抽取到工厂类中去，这就是策略模式+简单工厂
 * @author Jef
 * @date 2019/2/13
 */
public class ClientAfterTest {

    @Test
    public void testGetPrice() {
        List<Customer> customerList = CustomerBuilder.generatePriceList();
        for (Customer customer : customerList) {
            // 1：选择并创建需要使用的策略对象
            ICustomerStrategy ICustomerStrategy = CustomerTypeHandleFactory.getCustomerStrategy(customer.getType());
            // 2：创建上下文
            CustomerContext ctx = new CustomerContext(ICustomerStrategy);
            // 3：:计算报价
            double price = ctx.handlePrice(customer);
            System.out.println("原价" + customer.getGoodsPrice() + ",向客户报价：" + price);
        }
    }

    @Test
    public void testGetPriceV2() {
        List<Customer> customerList = CustomerBuilder.generatePriceList();
        CustomerTypeHandleFactory factory = new CustomerTypeHandleFactory();
        for (Customer customer : customerList) {
            // 1：选择并创建需要使用的策略对象
            ICustomerStrategy ICustomerStrategy = factory.getCustomerHandleStrategy(customer.getType());
            // 2：创建上下文
            CustomerContext ctx = new CustomerContext(ICustomerStrategy);
            // 3：:计算报价
            double price = ctx.handlePrice(customer);
            System.out.println("原价" + customer.getGoodsPrice() + ",向客户报价：" + price);
        }
    }

}