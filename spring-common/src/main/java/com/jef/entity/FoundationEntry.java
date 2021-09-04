package com.jef.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 基金分录
 * @author Jef
 * @date 2020/2/18
 */
public class FoundationEntry implements Serializable {

    private static final long serialVersionUID = 4390174287125946231L;

    /**
     * 基金编码
     */
    private String code;
    /**
     * 日期
     */
    private String createDate;
    /**
     * 净值
     */
    private BigDecimal unitPrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}