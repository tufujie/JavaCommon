package com.jef.business;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jef
 * @date 2019/4/28
 */
public class RentBreachVo implements Serializable {
    /**
     * 违约金周期
     */
    private Integer billingCycle;
    /**
     * 违约金类型
     */
    private Integer breachType;
    /**
     * 标准
     */
    private BigDecimal calStandard;

    public Integer getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Integer billingCycle) {
        this.billingCycle = billingCycle;
    }

    public Integer getBreachType() {
        return breachType;
    }

    public void setBreachType(Integer breachType) {
        this.breachType = breachType;
    }

    public BigDecimal getCalStandard() {
        return calStandard;
    }

    public void setCalStandard(BigDecimal calStandard) {
        this.calStandard = calStandard;
    }
}