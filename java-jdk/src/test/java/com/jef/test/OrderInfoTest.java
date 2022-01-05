package com.jef.test;

import com.jef.constant.BasicConstant;
import com.jef.dao.OrderInfoDao;
import com.jef.entity.OrderInfo;
import com.jef.entity.OrderProduct;
import com.jef.entity.User;
import com.jef.util.ConnectSessionUtil;
import com.jef.util.PrintUtil;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 订单测试
 *
 * @author Jef
 * @create 2018/5/16 9:33
 */
public class OrderInfoTest {
    /**
     * 测试通过id获取用户信息
     */
    @Test
    public void testGetOrderInfoById() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        OrderInfoDao orderInfoDao = session.getMapper(OrderInfoDao.class);
        OrderInfo orderInfo = orderInfoDao.selectByPrimaryKey(BasicConstant.ID);
        System.out.println("第三方订单号=" + orderInfo.getExtraOrderId());
    }

    /**
     * 测试通过唯一键获取用户信息
     */
    @Test
    public void testGetUserByShopAndExtraOrderId() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        String statement = "getByShopAndExtraOrderId";
        Map<String, Object> requestParams = Maps.newHashMap();
        requestParams.put("shopId", 1L);
        requestParams.put("extraOrderId", BasicConstant.FIRST_ORDER_ID);
        OrderInfo orderInfo = session.selectOne(statement, requestParams);
        System.out.println("第三方订单号=" + orderInfo.getExtraOrderId());
    }

    /**
     * 获取订单及订单商品
     */
    @Test
    public void testGetAllOrderInfo() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        OrderInfoDao orderInfoDao = session.getMapper(OrderInfoDao.class);
        List<OrderInfo> orderInfoList = orderInfoDao.getAllOrderInfo(Maps.newHashMap());
        for (OrderInfo orderInfo : orderInfoList) {
            System.out.println("订单号= " + orderInfo.getExtraOrderId() + " 包含商品：");
            List<OrderProduct> orderProducts = orderInfo.getOrderProductList();
            for (OrderProduct orderProduct : orderProducts) {
                System.out.println(orderProduct.getProductName() + " " + orderProduct.getNum() + " 件");
            }
            PrintUtil.printSplitLine();
        }
        System.out.println("end");
    }

    /**
     * 获取订单及订单商品
     */
    @Test
    public void testGetAllOrderInfoTwo() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        OrderInfoDao orderInfoDao = session.getMapper(OrderInfoDao.class);
        List<OrderInfo> orderInfoList = orderInfoDao.getAllOrderInfoTwo(Maps.newHashMap());
        for (OrderInfo orderInfo : orderInfoList) {
            System.out.println("订单号= " + orderInfo.getExtraOrderId() + " 包含商品：");
            List<OrderProduct> orderProducts = orderInfo.getOrderProductList();
            for (OrderProduct orderProduct : orderProducts) {
                System.out.println(orderProduct.getProductName() + " " + orderProduct.getNum() + " 件");
            }
            PrintUtil.printSplitLine();
        }
        System.out.println("end");
    }

    /**
     * 获取订单及用户
     */
    @Test
    public void testGetAllOrderInfoUser() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        OrderInfoDao orderInfoDao = session.getMapper(OrderInfoDao.class);
        List<OrderInfo> orderInfoList = orderInfoDao.getAllOrderInfoUser(Maps.newHashMap());
        for (OrderInfo orderInfo : orderInfoList) {
            String message = "订单号= " + orderInfo.getExtraOrderId() + " 购买者：";
            if (orderInfo.getUser() != null) {
                User user = orderInfo.getUser();
                message += user.getName();
            }
            System.out.println(message);
            PrintUtil.printSplitLine();
        }
        System.out.println("end");
    }
}
