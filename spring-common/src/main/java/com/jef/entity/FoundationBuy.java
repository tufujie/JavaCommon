package com.jef.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 基金
 * @author Jef
 * @date 2020/2/18
 */
public class FoundationBuy implements Serializable {
    private static final long serialVersionUID = 426886006561774919L;
    /**
     * id
     */
    private String id;
    /**
     * 基金编码
     */
    private String code;
    /**
     * 买入金额
     */
    private BigDecimal money;
    /**
     * 买入日期
     */
    private String buyDate;
    /**
     * 买入手续费
     */
    private BigDecimal buyRatePrice;
    /**
     * 买入时净值
     */
    private BigDecimal unitPrice;
    /**
     * 确认份额
     */
    private BigDecimal num;
    /**
     * 确认金额
     */
    private BigDecimal sureMoney;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public BigDecimal getBuyRatePrice() {
        return buyRatePrice;
    }

    public void setBuyRatePrice(BigDecimal buyRatePrice) {
        this.buyRatePrice = buyRatePrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public BigDecimal getSureMoney() {
        return sureMoney;
    }

    public void setSureMoney(BigDecimal sureMoney) {
        this.sureMoney = sureMoney;
    }
}