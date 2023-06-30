package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.designpattern.action.strategy.Customer;
import com.jef.designpattern.action.strategy.CustomerBuilder;

import org.junit.Test;

import java.util.List;

/**
 * 责任链模式测试
 * 通过责任链的处理方式，if-else结构也被我们消除了，每当新来了一种回执，只需要添加ICustopmerHandler实现类并修改CustomerHandlerContainer处理者容器即可，如果要使得程序符合开闭原则，
 * 则需要调整CustomerHandlerContainer中处理者的获取方式，通过反射的方式，获取指定包下的所有ICustomerHandler实现类。
 * 小结
 * if-else或switch case 这种分支判断的方式对于分支逻辑不多的简单业务，还是直观高效的。对于业务复杂，分支逻辑多，采用适当的模式技巧，会让代码更加清晰，容易维护，但同时类或方法数量也是倍增的。我们需要对业务做好充分分析，避免一上来就设计模式，避免过度设计！
 *
 * @author Jef
 * @date 2020/7/21 0021
 */
public class ClientTest {
    @Test
    public void testDuty() {
        // 模拟计费
        List<Customer> customerList = CustomerBuilder.generatePriceList();
        // 计费处理链对象
        CustomerHandlerChain customerHandlerChain = new CustomerHandlerChain();
        for (Customer customer : customerList) {
            double price = customerHandlerChain.handleCustomer(customer);
            System.out.println("原价" + customer.getGoodsPrice() + ",向客户报价：" + price);
        }
    }
}