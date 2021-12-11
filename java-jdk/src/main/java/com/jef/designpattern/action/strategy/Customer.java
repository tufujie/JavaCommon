package com.jef.designpattern.action.strategy;

/**
 * @author Jef
 * @date 2020/7/21 0021
 */
public class Customer {

    /**
     * 客户类型
     */
    String type;

    /**
     * 标准价格
     */
    private double goodsPrice;

    public Customer(String type, double goodsPrice) {
        this.type = type;
        this.goodsPrice = goodsPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}