package com.jef.jdk8;

import com.jef.constant.BasicList;
import com.jef.entity.OrderInfo;

import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Administrator
 * @date 2022/12/12
 */
public class FormCreatorNameGetFromCacheTest {

    @Test
    public void testFormCreatorNameGetFromCache() {
        List<OrderInfo> orderInfoList = BasicList.getOrderInfoList();
        System.out.println("表单条数=" + orderInfoList.size());
        System.out.println("设置创建人开始");
        FormCreatorNameGetFromCache<OrderInfo> formCreatorNameGetFromCache = new FormCreatorNameGetFromCache<OrderInfo>();
        formCreatorNameGetFromCache.setData(orderInfoList, OrderInfo::getCreatorID, OrderInfo::setCreatorName);
        System.out.println("设置创建人结束");
        for (OrderInfo orderInfo : orderInfoList) {
            System.out.println("id=" + orderInfo.getId() + ",创建人ID=" + orderInfo.getCreatorID() + ",创建人=" + orderInfo.getCreatorName());
        }
    }

}