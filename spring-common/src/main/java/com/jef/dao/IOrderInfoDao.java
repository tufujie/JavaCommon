package com.jef.dao;

import com.jef.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IOrderInfoDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    /**
     * 根据用户ID查询订单信息
     * @param userId
     * @return
     */
    List<OrderInfo> getByUserId(Long userId);

    /**
     * @return
     */
    List<OrderInfo> listOrderInfo(OrderInfo orderInfo);

    /**
     * @return
     */
    List<OrderInfo> listOrderInfoBySplitTable(OrderInfo orderInfo);

    List<OrderInfo> listOrderInfoBySplitTableV3(@Param("shopId") Long shopId, @Param("dbName") String dbName, @Param("tbName") String tbName);
}