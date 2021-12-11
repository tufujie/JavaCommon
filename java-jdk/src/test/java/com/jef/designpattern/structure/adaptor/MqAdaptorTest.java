package com.jef.designpattern.structure.adaptor;

import com.jef.constant.BasicConstant;
import com.jef.entity.MQInfo;
import com.jef.entity.OrderInfo;
import com.jef.util.MQUtil;
import com.jef.util.PrintUtil;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Jef
 * @date 2021/12/7
 */
public class MqAdaptorTest {

    @Test
    public void testMqAdaptor() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        OrderInfo orderMq = new OrderInfo();
        orderMq.setUid("000001");
        orderMq.setSku("10000000001");
        orderMq.setOrderId("10000000000001");
        orderMq.setCreateOrderTime(new Date());
        HashMap<String, String> link = new HashMap<String, String>();
        link.put("userId", "uid");
        link.put("bizId", "orderId");
        link.put("bizTime", "createOrderTime");
        link.put("mqAdaptor", BasicConstant.BillAdaptorEnum.CREATE_ORDER.getAdaptor());
        MQInfo mqInfo = MQAdapter.filter(JSON.toJSONString(orderMq), link);
        System.out.println("mq.orderMq(适配前)" + JSON.toJSONString(orderMq));
        System.out.println("mq.orderMq(适配后)" + JSON.toJSONString(mqInfo));
        PrintUtil.printSplitLine();
        MQUtil.handlerBill(mqInfo);

    }

}