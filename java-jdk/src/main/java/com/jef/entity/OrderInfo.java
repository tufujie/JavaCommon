package com.jef.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单信息
 *
 * @author Jef
 * @create 2018/5/15 19:18
 */
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = -7738420644752056965L;
    private Long id;

    private String extraOrderId;

    private Long shopId;

    private BigDecimal totalPrice;

    private BigDecimal totalInPrice;

    /**
     * 订单商品
     * @return
     */
    private List<OrderProduct> orderProductList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtraOrderId() {
        return extraOrderId;
    }

    public void setExtraOrderId(String extraOrderId) {
        this.extraOrderId = extraOrderId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalInPrice() {
        return totalInPrice;
    }

    public void setTotalInPrice(BigDecimal totalInPrice) {
        this.totalInPrice = totalInPrice;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
