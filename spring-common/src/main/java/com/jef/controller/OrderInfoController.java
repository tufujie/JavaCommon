package com.jef.controller;

import com.google.common.collect.ImmutableMap;
import com.jef.dao.IOrderInfoDao;
import com.jef.dao.IOrderProductDao;
import com.jef.dao.IUserDao;
import com.jef.dao.IShopDao;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.OrderInfo;
import com.jef.entity.OrderProduct;
import com.jef.entity.Shop;
import com.jef.entity.User;
import com.jef.property.cache.UserCache;
import com.jef.service.IOrderInfoService;
import com.jef.util.REJSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 订单控制类
 *
 * @author Jef
 * @create 2018/6/12 20:26
 */
@Controller
@RequestMapping(value = "orderInfo")
public class OrderInfoController {
    @Autowired
    private IUserDao userDao;
    @Autowired
    private IOrderProductDao orderProductDao;
    @Autowired
    private IShopDao shopDao;
    @Autowired
    private IOrderInfoService orderInfoService;

    @RequestMapping(value = "getOrderInfoByUserId/{userId}", method = RequestMethod.GET)
    public ModelAndView getOrderInfoByUserId(@PathVariable(value = "userId") Long userId, ModelAndView mv, Model
            model) throws Exception {
//        User user = userDao.selectByPrimaryKey(userId);
        // 使用缓存获取用户信息
        User user = UserCache.getUser(userDao, userId);
        List<OrderInfo> orderInfoList = orderInfoService.getByUserId(user.getId());
        StringBuilder sb = new StringBuilder();
        sb.append("用户" + user.getName() + "购买了如下商品：<br>");
        for (OrderInfo orderInfo : orderInfoList) {
            Shop shop = shopDao.selectByPrimaryKey(orderInfo.getShopId());
            sb.append("店铺名称:" + shop.getName() + "<br>订单号:" + orderInfo.getExtraOrderId() + "<br>");
            List<OrderProduct> orderProducts = orderProductDao.getByOrderId(orderInfo.getId());
            for (OrderProduct orderProduct : orderProducts) {
                sb.append("\n商品:" + orderProduct.getProductName() + ",件数:" + orderProduct.getNum() + "<br>");
            }
        }
        model.addAttribute("buyThing", sb.toString());
        mv.setViewName("orderInfo/orderInfo");
        return mv;
    }

}
