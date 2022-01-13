package com.jef.service.impl;

import com.jef.common.interceptor.SplitTablePlugin;
import com.jef.dao.IOrderInfoBaseDao;
import com.jef.dao.IOrderInfoDao;
import com.jef.dao.IOrderProductDao;
import com.jef.daoOne.IOrderInfoOneDao;
import com.jef.daoTwo.IOrderInfoTwoDao;
import com.jef.dbRouting.annotation.Router;
import com.jef.entity.OrderInfo;
import com.jef.service.IOrderInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单服务，用于N中功能的测试
 *
 * @author Jef
 * @create 2018/5/15 19:21
 */

@Service(value = "orderInfoService")
public class OrderInfoServiceImpl implements IOrderInfoService {
    @Autowired
    private IOrderInfoDao orderInfoDao;
    @Resource
    private IOrderProductDao orderProductDao;
    @Resource
    private IOrderInfoOneDao orderInfoOneDao;
    @Resource
    private IOrderInfoTwoDao orderInfoTwoDao;

    @Router
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public List<OrderInfo> listOrderInfo(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception {
        return orderInfoDao.listOrderInfo(orderInfo);
    }

    @Override
    public List<OrderInfo> getByUserId(Long userID, String table) {
        return orderInfoDao.getByUserId(userID);
    }

    @Override
    public List<OrderInfo> getByUserId(Long userID) {
        return orderInfoDao.getByUserId(userID);
    }

    @Override
    public List<OrderInfo> getOrderInfoBySplitTable(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception {
        SplitTablePlugin.setSplitRule(orderInfo.getShopId(), new String[]{"order_info"});
        return orderInfoDao.listOrderInfoBySplitTable(orderInfo);
    }

    @Override
    public List<OrderInfo> getOrderInfoBySplitTableV3(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception {
        // 分两库两表all_test、all_test_read,每个库中又有两个表order_info、order_info_1，我们根据账户id模4的取模值来分库表，1：all_test.order_info_1;2:all_test_read.order_info;3:all_test_read.order_info_1;4:all_test.order_info
        String dbName = orderInfo.getShopId() % 4 < 2 ? "all_test" : "all_test_read";
        String tbName = orderInfo.getShopId() % 2 == 0 ? "order_info" : "order_info_1";
        return orderInfoDao.listOrderInfoBySplitTableV3(orderInfo.getShopId(), dbName, tbName);
    }

    /**
     * 获取具体的dao
     * @param shopId
     * @return
     */
    IOrderInfoBaseDao selectDao(Long shopId) {
        return shopId % 4 < 2 ? orderInfoOneDao : orderInfoTwoDao;
    }

    @Override
    public List<OrderInfo> getOrderInfoBySplitTableV4(OrderInfo orderInfo, int startPageNum, int pageCountNum) throws Exception {
        // 分两库两表all_test、all_test_read,每个库中又有两个表order_info、order_info_1，我们根据账户id模4的取模值来分库表，1：all_test.order_info_1;2:all_test_read.order_info;3:all_test_read.order_info_1;4:all_test.order_info
        String tbName = orderInfo.getShopId() % 2 == 0 ? "order_info" : "order_info_1";
        return selectDao(orderInfo.getShopId()).listOrderInfoBySplitTableV4(orderInfo.getShopId(), tbName);
    }
}
