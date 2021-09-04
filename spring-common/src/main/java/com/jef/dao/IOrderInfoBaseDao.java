package com.jef.dao;

import com.jef.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Jef
 * @date: 2021/4/16 20:10
 */
public interface IOrderInfoBaseDao {

    /**
     * @return
     */
    List<OrderInfo> listOrderInfoBySplitTableV3(@Param("shopId") Long shopId, @Param("dbName") String dbName, @Param("tbName") String tbName);

    List<OrderInfo> listOrderInfoBySplitTableV4(@Param("shopId") Long shopId, @Param("tbName") String tbName);

}