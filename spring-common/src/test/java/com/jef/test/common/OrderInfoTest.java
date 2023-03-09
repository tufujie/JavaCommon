package com.jef.test.common;

import com.jef.common.context.SpringContextHolder;
import com.jef.entity.OrderInfo;
import com.jef.service.impl.OrderInfoServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Jef
 * @date 2022/1/13
 */
public class OrderInfoTest extends BaseTest {

    @Override
    public void init() throws Exception {

    }

    /**
     * 获取订单信息
     * 模拟service调用
     *
     * @date 2022/01/12
     */
    @Test
    public void testGetOrderInfoBySplitTable() throws Exception {
        initMybatis("mapper/*Mapper.xml");
        TestBeanUtil.addBean(OrderInfoServiceImpl.class.getSimpleName(), OrderInfoServiceImpl.class);
        OrderInfoServiceImpl orderInfoService = SpringContextHolder.getBean(OrderInfoServiceImpl.class.getSimpleName());
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setShopId(2L);
        List<OrderInfo> orderInfos = orderInfoService.getOrderInfoBySplitTable(orderInfo, 1, 10);
        System.out.println("店铺号=" + +orderInfo.getShopId() + "，订单数量=" + orderInfos.size());
        orderInfo.setShopId(1L);
        orderInfos = orderInfoService.getOrderInfoBySplitTable(orderInfo, 1, 10);
        System.out.println("店铺号=" + +orderInfo.getShopId() + "，订单数量=" + orderInfos.size());

    }


}