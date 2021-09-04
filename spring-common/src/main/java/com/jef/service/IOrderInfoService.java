package com.jef.service;

import com.jef.entity.OrderInfo;
import java.util.List;
import java.util.Map;

public interface IOrderInfoService {

    /**
     * 查询订单列表
     * @author Jef
     * @date 2020/8/27
     * @param queryMap
     * @param startPageNum
     * @param pageCountNum
     * @return java.util.List<com.jef.entity.User>
     */
    List<OrderInfo> listOrderInfo(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception;

    List<OrderInfo> getOrderInfoBySplitTable(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception;

    List<OrderInfo> getOrderInfoBySplitTableV3(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception;

    List<OrderInfo> getOrderInfoBySplitTableV4(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception;

    List<OrderInfo> getByUserId(Long userID, String table);

    List<OrderInfo> getByUserId(Long userID);
}
