package com.jef.jdk8;

import com.google.common.collect.Maps;
import com.jef.entity.OrderInfo;
import com.jef.entity.User;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * 避免多重null判断的神器
 * @author Jef
 * @date 2019/3/14
 */
public class OptionalTest {

    @Test
    public void testOoptional() {
        User user = new User();
        /*
        * 不为空执行ifPresent，否则不执行
        * 常用于对象的非空判断
        * */
        Optional.ofNullable(user.getPhone()).ifPresent(obj -> {
            System.out.println("userPhone1=" + user.getPhone());
        });
        /*
        * 不为空取前面这个值，为空取后面这个值
        * 常用于数字为空时进行默认值的设置
        * */
        user.setPhone(Optional.ofNullable(user.getPhone()).orElse("1326686****"));
        Optional.ofNullable(user.getPhone()).ifPresent(obj -> {
            System.out.println("userPhone2=" + user.getPhone());
        });
        System.out.println("手机号=" + user.getPhone());
        user.setPhone("13266860001");
        user.setPhone(Optional.ofNullable(user.getPhone()).orElse("1326686****"));
        System.out.println("手机号=" + user.getPhone());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setExtraOrderId("test123");
        user.setOrderInfo(orderInfo);
        // 创建Optional，如果参数为null，将抛空指针。
        Optional.of(user)
                .map(a -> a.getOrderInfo())
                .map(b -> b.getExtraOrderId())
                .ifPresent(e -> {
                    System.out.println("订单号=" + user.getOrderInfo().getExtraOrderId());
                }
        );
        orderInfo = Optional.ofNullable(orderInfo).orElse(new OrderInfo());
        System.out.println("orderInfo.getExtraOrderId v1=" + orderInfo.getExtraOrderId());
        OrderInfo orderInfo2 = null;
        orderInfo = Optional.ofNullable(orderInfo2).orElse(orderInfo);
        System.out.println("orderInfo.getExtraOrderId v2=" + orderInfo.getExtraOrderId());
        Map<String, Object> map = Maps.newHashMap();
        map.put("key2", "test");
        // 创建Optional，如果参数可以为null
        try {
            String key1 = (String) Optional.ofNullable(map.get("key1"))
                    .orElseThrow(() -> new RuntimeException("查询的key1不存在"));
            System.out.println(key1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String key2 = (String) Optional.ofNullable(map.get("key2"))
                .orElseGet(() -> UUID.randomUUID().toString());
        System.out.println(key2);

    }


}