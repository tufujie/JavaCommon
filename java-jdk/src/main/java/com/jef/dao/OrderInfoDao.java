package com.jef.dao;

import com.jef.entity.OrderInfo;

import java.util.List;
import java.util.Map;

/**
 * 订单DAO层
 *
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface OrderInfoDao {
    /**
     * 根据ID查询订单信息
     * @param id
     * @return
     */
    OrderInfo selectByPrimaryKey(Long id);

    /**
     * 根据店铺ID和订单号查询订单信息
     * @param requestParams
     * @return
     */
    OrderInfo getByShopAndExtraOrderId(Map<String, Object> requestParams);

    /**
     * 根据用户ID查询订单信息
     * @param userId
     * @return
     */
    List<OrderInfo> getByUserId(Long userId);

    /**
     * 查询订单信息
     */
    List<OrderInfo> getAllOrderInfo(Map<String, Object> requestParams);

    /**
     * 查询订单信息
     */
    List<OrderInfo> getAllOrderInfoTwo(Map<String, Object> requestParams);
    /**
     * 查询订单信息
     */
    List<OrderInfo> getAllOrderInfoUser(Map<String, Object> requestParams);

}
