package com.jef.designpattern.structure.adaptor;

import com.jef.entity.MQInfo;

/**
 * @author Jef
 * @date 2021/12/7
 */
public class OrderAdaptor {

    /**
     * 创建订单
     *
     * @param mqInfo
     */
    public void createOrder(MQInfo mqInfo) {
        System.out.println("开始创建订单,订单ID=" + mqInfo.getBizId());
    }

}