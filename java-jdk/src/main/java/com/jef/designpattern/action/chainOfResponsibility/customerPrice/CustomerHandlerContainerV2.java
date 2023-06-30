package com.jef.designpattern.action.chainOfResponsibility.customerPrice;

import com.jef.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 至此，该方案完美符合了开闭原则，如果新增一个回执类型，只需要添加一个新的处理器即可，无需做其它改动。
 * 策略模式+注解
 * 此方案其实和上述没有太大异同，为了能符合开闭原则，通过自定义注解的方式，标记处理者类，然后反射获取到该类集合，放到Map容器中，这里不再赘述
 *
 * @author Jef
 * @date 2020/7/21 0021
 */
public class CustomerHandlerContainerV2 {

    private CustomerHandlerContainerV2() {
    }

    public static List<ICustomerHandler> getReceiptHandlerList() {
        List<ICustomerHandler> receiptHandlerList = new ArrayList<>();
        //获取IReceiptHandler接口的实现类
        Set<Class<?>> classList = ReflectionUtil.getClassSetBySuper(ICustomerHandler.class);
        if (classList != null && classList.size() > 0) {
            for (Class<?> clazz : classList) {
                try {
                    receiptHandlerList.add((ICustomerHandler) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return receiptHandlerList;
    }

}