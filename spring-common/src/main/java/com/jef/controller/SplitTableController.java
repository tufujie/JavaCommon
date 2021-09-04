package com.jef.controller;

import com.google.common.collect.ImmutableMap;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.OrderInfo;
import com.jef.service.IOrderInfoService;
import com.jef.util.REJSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 分表controller
 *
 * @author Jef
 * @date 2021/4/3
 */
@Controller
@RequestMapping(value = "/splitTable")
public class SplitTableController {
    @Autowired
    private IOrderInfoService orderInfoService;

    @ResponseBody
    @RequestMapping(value = "getOrderInfoListByShopIDUseSplitTable", method = RequestMethod.GET)
    public BaseJSONVo getOrderInfoListByShopIDUseSplitTable(@RequestParam(value = "shopId") Long shopId) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setShopId(shopId);
        orderInfo.setUserNum(shopId.toString());
        List<OrderInfo> orderInfoList = orderInfoService.listOrderInfo(orderInfo, -1, -1);
        return REJSONUtils.success(orderInfoList, 0, "操作成功");
    }

    @ResponseBody
    @RequestMapping(value = "getOrderInfoListByShopIDUseSplitTableV2", method = RequestMethod.GET)
    public BaseJSONVo getOrderInfoListByShopIDUseSplitTableV2(@RequestParam(value = "shopId") Long shopId) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setShopId(shopId);
        List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoBySplitTable(orderInfo, -1, -1);
        return REJSONUtils.success(orderInfoList, 0, "操作成功");
    }

    @ResponseBody
    @RequestMapping(value = "getOrderInfoListByShopIDUseSplitTableV3", method = RequestMethod.GET)
    public BaseJSONVo getOrderInfoListByShopIDUseSplitTableV3(@RequestParam(value = "shopId") Long shopId) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setShopId(shopId);
        List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoBySplitTableV3(orderInfo, -1, -1);
        return REJSONUtils.success(orderInfoList, 0, "操作成功");
    }

    @ResponseBody
    @RequestMapping(value = "getOrderInfoListByShopIDUseSplitTableV4", method = RequestMethod.GET)
    public BaseJSONVo getOrderInfoListByShopIDUseSplitTableV4(@RequestParam(value = "shopId") Long shopId) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setShopId(shopId);
        List<OrderInfo> orderInfoList = orderInfoService.getOrderInfoBySplitTableV4(orderInfo, -1, -1);
        return REJSONUtils.success(orderInfoList, 0, "操作成功");
    }


    @RequestMapping(value = "/splitTable")
    public ModelAndView introduce(ModelAndView mv) {
        mv.setViewName("splitTable/splitTable");
        return mv;
    }


}