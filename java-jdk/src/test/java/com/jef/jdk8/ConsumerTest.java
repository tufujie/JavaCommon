package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import com.jef.entity.OrderInfo;
import com.jef.util.NumberUtils;
import com.jef.util.StringUtils;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 消费消息
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
        System.out.println("------");

        Consumer<String> c2 = StringUtils::printString;
        // 会进行输出
        c2.accept(BasicConstant.HELLO_WORLD);
        // 会进行输出
        c2.accept("");
        System.out.println("------");

        BiConsumer<String, Integer> c3 = (s, i) -> {
            if (s.length() > i) {
                StringUtils.printString(s);
            }
        };
        c3.accept(BasicConstant.HELLO_WORLD, 1);
        c3.accept(BasicConstant.HELLO_WORLD, 100);
        System.out.println("------");

        BiConsumer<String, String> c4 = (s1, s2) -> StringUtils.printString(s1 + s2);
        c4.accept(BasicConstant.HELLO_WORLD, BasicConstant.USER_NAME);

    }
}