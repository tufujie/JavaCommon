package com.jef.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 基金
 * @author Jef
 * @date 2020/2/18
 */
public class FoundationShop implements Serializable {
    private static final long serialVersionUID = -3697485755771776966L;
    /**
     * id
     */
    private String id;
    /**
     * 基金编码
     */
    private String code;
    /**
     * 买入日期
     */
    private String shopDate;
    /**
     * 卖出手续费
     */
    private BigDecimal shopRatePrice;
    /**
     * 卖出手续费
     */
    private String linkBuyDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShopDate() {
        return shopDate;
    }

    public void setShopDate(String shopDate) {
        this.shopDate = shopDate;
    }

    public BigDecimal getShopRatePrice() {
        return shopRatePrice;
    }

    public void setShopRatePrice(BigDecimal shopRatePrice) {
        this.shopRatePrice = shopRatePrice;
    }

    public String getLinkBuyDate() {
        return linkBuyDate;
    }

    public void setLinkBuyDate(String linkBuyDate) {
        this.linkBuyDate = linkBuyDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}