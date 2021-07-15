package com.jef.jdk8;

import com.jef.constant.BasicList;
import com.jef.entity.OrderInfo;
import com.jef.entity.User;
import com.jef.util.NumberUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

/**
 * reduce操作
 * reduce聚合，集合reduce,将集合中每个元素聚合成一条数据，通常用于求和
 * reduce 操作可以实现从一组值中生成一个值。在上述例子中用到的 count、 min 和 max 方 法，因为常用而被纳入标准库中。事实上，这些方法都是 reduce 操作。及早求值。
 * @author Jef
 * @date 2020/1/17
 */
public class ReduceTest {

    @Test
    public void testAddNumber() {
        List<OrderInfo> orderInfoList = BasicList.getOrderInfoList();
        BigDecimal totalAmount = orderInfoList.stream().map(OrderInfo::getTotalPrice).reduce(BigDecimal.ZERO, NumberUtils::add);
        System.out.println(totalAmount);
        BigDecimal totalAmount2 = orderInfoList.stream().map(OrderInfo::getTotalPrice).reduce(NumberUtils::add).orElse(BigDecimal.ZERO);
        System.out.println(totalAmount2);
        BigDecimal totalAmount3 = orderInfoList.stream().reduce(BigDecimal.ZERO, (sumAmount, orderInfo) -> NumberUtils.add(sumAmount, orderInfo.getTotalPrice()), NumberUtils::add);
        System.out.println(totalAmount3);
    }

    @Test
    public void testAddString() {
        List<User> users = BasicList.getUserList();
        String name = users.stream().map(User::getName).reduce("名字集合", (a, b) -> a + b);
//        我们看得reduce接收了一个初始值为"名字集合"的累加器，依次取出值与累加器相加，最后累加器的值就是最终的结果。
        System.out.println(name);
    }
}