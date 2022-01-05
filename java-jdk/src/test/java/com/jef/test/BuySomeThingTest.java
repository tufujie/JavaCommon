package com.jef.test;

import com.jef.constant.BasicConstant;
import com.jef.dao.OrderInfoDao;
import com.jef.dao.OrderProductDao;
import com.jef.dao.ShopDao;
import com.jef.dao.UserDao;
import com.jef.entity.OrderInfo;
import com.jef.entity.OrderProduct;
import com.jef.entity.Shop;
import com.jef.entity.User;
import com.jef.util.ConnectSessionUtil;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * 全部测试
 *
 * @author Jef
 * @create 2018/5/16 9:48
 */
public class BuySomeThingTest {
    /**
     * 一次性获取用户Jef购买的所有信息，包括店铺，订单号，商品，数量
     */
    @Test
    public void getAllBuy() {
        SqlSession session = ConnectSessionUtil.getSqlSession();
        UserDao userDao = session.getMapper(UserDao.class);
        OrderInfoDao orderInfoDao = session.getMapper(OrderInfoDao.class);
        OrderProductDao orderProductDao = session.getMapper(OrderProductDao.class);
        ShopDao shopDao = session.getMapper(ShopDao.class);
        Map<String, Object> requestParams = Maps.newHashMap();
        String name = BasicConstant.USER_NAME;
        String phone = BasicConstant.USER_PHONE;
        requestParams.put("name", name);
        requestParams.put("phone", phone);
        User user = userDao.getByNameAndPhone(requestParams);
        List<OrderInfo> orderInfoList = orderInfoDao.getByUserId(user.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("用户" + name + "购买了如下商品：\n");
        for (OrderInfo orderInfo : orderInfoList) {
            Shop shop = shopDao.selectByPrimaryKey(orderInfo.getShopId());
            sb.append("店铺名称:" + shop.getName() + ",订单号:" + orderInfo.getExtraOrderId());
            List<OrderProduct> orderProducts = orderProductDao.getByOrderId(orderInfo.getId());
            for (OrderProduct orderProduct : orderProducts) {
                sb.append("\n商品:" + orderProduct.getProductName() + ",件数:" + orderProduct.getNum());
            }
        }
        System.out.println(sb);
    }

}
