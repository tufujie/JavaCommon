package com.jef.jdk8;

import com.jef.constant.BasicConstant;
import com.jef.entity.OrderInfo;
import com.jef.entity.User;
import com.jef.util.PrintUtil;

import com.google.common.collect.Maps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * 避免多重null判断的神器
 *
 * @author Jef
 * @date 2019/3/14
 */
public class OptionalTest {

    /**
     * ifPresent(Consumer consumer)：如果option对象保存的值不是null，则调用consumer对象，否则不调用
     */
    @Test
    public void testIfPresent() {
        User user = new User();
        /*
         * 不为空执行ifPresent，否则不执行
         * 常用于对象的非空判断
         * */
        boolean exist = Optional.ofNullable(user.getPhone()).isPresent();
        if (exist) {
            System.out.println("手机号已设置");
            Optional.ofNullable(user.getPhone()).ifPresent(obj -> {
                System.out.println("userPhone为null=" + user.getPhone());
            });
        } else {
            System.out.println("手机号未设置");
        }

        /*
         * 不为空取前面这个值，为空取后面这个值
         * 常用于数字为空时进行默认值的设置
         * */
        user.setPhone(Optional.ofNullable(user.getPhone()).orElse("1326686****"));
        Optional.ofNullable(user.getPhone()).ifPresent(obj -> {
            System.out.println("userPhone非空=" + user.getPhone());
        });
        System.out.println("手机号未设置前=" + user.getPhone());
        user.setPhone("13266860001");
        System.out.println("手机号设置后=" + user.getPhone());
        user.setPhone("");
        Optional.ofNullable(user.getPhone()).ifPresent(obj -> {
            System.out.println("userPhone空字符串=" + user.getPhone());
        });
    }

    @Test
    public void testOfNullable() {
        // 参数不可以为null
        try {
            Optional<String> optionalS = Optional.of(null);
        } catch (Exception e) {
            PrintUtil.printf("error: %s", e);
        }
        Optional<String> optionalS = Optional.of(BasicConstant.USER_NAME);


        User user = new User();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setExtraOrderId("test123");
        user.setOrderInfo(orderInfo);
        // 参数可以为null
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
                    .orElseThrow(() -> new RuntimeException("查询的key1不设置"));
            System.out.println(key1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String key2 = (String) Optional.ofNullable(map.get("key2"))
                .orElseGet(() -> UUID.randomUUID().toString());
        System.out.println(key2);
    }

    /**
     * filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional。
     */
    @Test
    public void testFilter() {
        Optional<String> optional1 = Optional.ofNullable(BasicConstant.USER_NAME);
        Optional<String> filter1 = optional1.filter((obj) -> BasicConstant.USER_NAME.equals(obj));
        Optional<String> filter2 = optional1.filter((obj) -> BasicConstant.STR_ONE.equals(obj));
        Assertions.assertTrue(filter1.isPresent());
        Assertions.assertTrue(filter1.get().equals(BasicConstant.USER_NAME));
        Assertions.assertFalse(filter2.isPresent());

        Optional<String> optional2 = Optional.ofNullable(null);
        Optional<String> filter3 = optional2.filter((obj) -> obj == null);
        Assertions.assertFalse(filter3.isPresent());
    }

    /**
     * map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)。创建Optional，如果参数为null，将抛空指针。
     */
    @Test
    public void testMap() {
        User user = new User();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setExtraOrderId("test123");
        user.setOrderInfo(orderInfo);
        Optional.of(user)
                .map(a -> a.getOrderInfo())
                .map(b -> b.getExtraOrderId())
                .ifPresent(e -> {
                            System.out.println("订单号=" + user.getOrderInfo().getExtraOrderId());
                        }
                );
        // 等效于下面的写法
        Optional<OrderInfo> optionalUser = Optional.of(user)
                .map(a -> a.getOrderInfo());
        Optional<String> optionalString = optionalUser.map(b -> b.getExtraOrderId());
        optionalString.ifPresent(obj -> {
            System.out.println("订单号=" + obj);
        });
        // 实现具体的功能，例如：字符串拼接
        Optional<String> optional1 = Optional.ofNullable(BasicConstant.USER_NAME);
        Optional<String> optional2 = optional1.map(obj -> obj + BasicConstant.STR_ONE);
        Assertions.assertTrue(optional2.get().equals(BasicConstant.USER_NAME + BasicConstant.STR_ONE));

        // flatMap()：功能与map()相似，差别请看如下代码。flatMap方法与map方法类似，区别在于mapping函数的返回值不同。map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional
        Optional<Optional<String>> str1Optional = optional1.map(obj -> Optional.of("key" + obj));

        Optional<String> str2Optional = optional1.flatMap(obj -> Optional.of("key" + obj));
        // keyJef
        System.out.println(str1Optional.get().get());
        // keyJef
        System.out.println(str2Optional.get());
    }


}