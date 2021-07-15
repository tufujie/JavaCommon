package com.jef.dao;


import com.jef.entity.OrderProduct;
import java.util.List;

/**
 * 子订单DAO层
 *
 * @author Jef
 * @create 2018/5/15 19:18
 */
public interface OrderProductDao {
    /**
     * 根据ID查询子订单信息
     * @param id
     * @return
     */
    OrderProduct selectByPrimaryKey(Long id);

    /**
     * 根据订单ID查询子订单信息
     * @param orderId
     * @return
     */
    List<OrderProduct> getByOrderId(Long orderId);
}
