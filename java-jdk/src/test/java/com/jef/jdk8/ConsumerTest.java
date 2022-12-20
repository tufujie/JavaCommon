package com.jef.jdk8;

import com.jef.business.BusinessDemo;
import com.jef.constant.BasicConstant;
import com.jef.entity.OrderInfo;
import com.jef.util.NumberUtils;
import com.jef.util.PrintUtil;
import com.jef.util.StringUtils;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 消费消息
 * 有参无返回值，用于消费数据
 *
 * @author Jef
 * @date 2019/3/14
 */
public class ConsumerTest {
    @Test
    public void testAccept() {
        Consumer<String> consumer = System.out::println;
        consumer.accept(BasicConstant.HELLO_WORLD);
        /**
         * 设置9折优惠
         */
        Consumer<OrderInfo> consumerDiscount = new Consumer<OrderInfo>() {
            @Override
            public void accept(OrderInfo orderInfo) {
                orderInfo.setTotalPrice(NumberUtils.multiply(orderInfo.getTotalPrice(), new BigDecimal(0.9), 2));
            }
        };

        /**
         * 设置5元优惠
         */
        Consumer<OrderInfo> consumerSub = new Consumer<OrderInfo>() {
            @Override
            public void accept(OrderInfo orderInfo) {
                orderInfo.setTotalPrice(NumberUtils.subtract(orderInfo.getTotalPrice(), new BigDecimal(5)));
            }
        };
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setTotalPrice(new BigDecimal(100));
        consumerDiscount.andThen(consumerSub).accept(orderInfo);
        System.out.println("折扣优惠后的价格为=" + orderInfo.getTotalPrice());
        // 使用字典方式
        Map<Integer, Consumer> consumerMap = new HashMap<>();
        consumerMap.put(1, consumerDiscount);
        consumerMap.put(2, consumerSub);
        orderInfo.setTotalPrice(new BigDecimal(200));
        orderInfo.setDiscountType(1);
        consumerMap.get(orderInfo.getDiscountType()).accept(orderInfo);
        orderInfo.setDiscountType(2);
        consumerMap.get(orderInfo.getDiscountType()).accept(orderInfo);
        System.out.println("折扣优惠后的价格为=" + orderInfo.getTotalPrice());
    }

    @Test
    public void testMain() {
        Consumer<String> c1 = s -> {
            // 具体的功能
            if (!s.isEmpty()) {
                StringUtils.printString(s);
            }
        };
        // 会进行输出
        c1.accept(BasicConstant.HELLO_WORLD);
        // 不会进行输出
        c1.accept("");
        PrintUtil.printSplitLine();

        Consumer<String> c2 = StringUtils::printString;
        // 会进行输出
        c2.accept(BasicConstant.HELLO_WORLD);
        // 会进行输出
        c2.accept("");
        PrintUtil.printSplitLine();

        BiConsumer<String, Integer> c3 = (s, i) -> {
            // 对两个传参进行处理
            if (s.length() > i) {
                StringUtils.printString("BiConsumer=" + s);
            }
        };
        c3.accept(BasicConstant.HELLO_WORLD, 1);
        c3.accept(BasicConstant.HELLO_WORLD, 100);
        PrintUtil.printSplitLine();

        BiConsumer<String, String> c4 = (s1, s2) -> StringUtils.printString("BiConsumer拼接=" + s1 + s2);
        c4.accept(BasicConstant.HELLO_WORLD, BasicConstant.USER_NAME);

        PrintUtil.printSplitLine();
        Consumer<Integer> consumer = BusinessDemo::taskNoReturn;
        consumer.accept(5);

        PrintUtil.printSplitLine();
        Consumer<String> andThan1 = obj -> {
            System.out.println(obj + "转换为大写" + obj.toUpperCase());
        };
        Consumer<String> andThan2 = obj -> {
            System.out.println(obj + "转换为小写" + obj.toLowerCase());
        };
        // andThan1先执行，执行完毕再执行andThan2
        andThan1.andThen(andThan2).accept(BasicConstant.USER_NAME);

    }
}