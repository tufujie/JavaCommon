package com.jef.collection.listdemo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jef.entity.OrderInfo;
import com.jef.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 集合排序
 *
 * @author Jef
 * @date 2018/9/28 17:18
 */
public class ListSortDemo {
    public static void main(String[] args) {

    }

    public void sortOne() {
        Map<String, OrderInfo> orderInfoMap = Maps.newHashMap();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setId(1L);
        orderInfo.setExtraOrderId("1");
        orderInfo.setTotalInPrice(new BigDecimal(10));
        orderInfo.setTotalPrice(new BigDecimal(12));
        orderInfoMap.put(orderInfo.getExtraOrderId(), orderInfo);

        OrderInfo orderInfo2 = new OrderInfo();
        orderInfo2.setId(2L);
        orderInfo2.setExtraOrderId("2");
        orderInfo2.setTotalInPrice(new BigDecimal(10));
        orderInfo2.setTotalPrice(new BigDecimal(11));
        orderInfoMap.put(orderInfo2.getExtraOrderId(), orderInfo2);

        OrderInfo orderInfo3 = new OrderInfo();
        orderInfo3.setId(3L);
        orderInfo3.setExtraOrderId("3");
        orderInfo3.setTotalInPrice(new BigDecimal(10));
        orderInfo3.setTotalPrice(new BigDecimal(5));
        orderInfoMap.put(orderInfo3.getExtraOrderId(), orderInfo3);
        List<OrderInfo> orderInfoList = Lists.newArrayList();
        orderInfoList.add(orderInfo);
        orderInfoList.add(orderInfo2);
        orderInfoList.add(orderInfo3);
        for (OrderInfo orderInfo1 : orderInfoList) {
            System.out.println(orderInfo1.getTotalPrice());
        }
        System.out.println("end");
        Collections.sort(orderInfoList, new Comparator<OrderInfo>() {
            @Override
            public int compare(OrderInfo o1, OrderInfo o2) {
                return NumberUtils.compareValue(NumberUtils.divide(o2.getTotalInPrice(), o2.getTotalPrice(), 2),
                        NumberUtils.divide(o1.getTotalInPrice(), o1.getTotalPrice(), 2));
            }
        });
        for (OrderInfo orderInfo1 : orderInfoList) {
            System.out.println(orderInfo1.getTotalPrice());
        }
    }
}
