package com.jef.business;

import com.jef.util.LogicUtils;
import com.jef.util.NumberUtils;

import java.math.BigDecimal;

/**
 * @author Jef
 * @date 2019/4/28
 */
public class ReceiveHelper {
    public BigDecimal getPenalAmount(BigDecimal renoAmount, long diffDay, RentBreachVo rentBreachVo) {
        if (LogicUtils.isNotNull(rentBreachVo.getBreachType()) && rentBreachVo.getBreachType() == 1) {
            // 按固定金额
            if (rentBreachVo.getBillingCycle() == 0) {
                // 按天
                renoAmount = NumberUtils.multiply(rentBreachVo.getCalStandard(), diffDay, 2);
            } else if (rentBreachVo.getBillingCycle() == 1) {
                // 按月
                renoAmount = NumberUtils.multiply(rentBreachVo.getCalStandard(), NumberUtils.divide(diffDay * 12, 365, 5), 2);
            } else if(rentBreachVo.getBillingCycle() == 2) {
                // 按年
                renoAmount = NumberUtils.multiply(rentBreachVo.getCalStandard(), NumberUtils.divide(diffDay, 365, 5), 2);
            }
        } else {
            // 按百分比
            if (rentBreachVo.getBillingCycle() == 0) {
                // 按天
                renoAmount = NumberUtils.divide(NumberUtils.multiply(NumberUtils.multiply(renoAmount, diffDay, 2), rentBreachVo.getCalStandard(), 2), 100, 5);
            } else if (rentBreachVo.getBillingCycle() == 1) {
                // 按月
                renoAmount = NumberUtils.divide(NumberUtils.multiply(NumberUtils.multiply(renoAmount, NumberUtils.divide(diffDay * 12, 365, 5), 2), rentBreachVo.getCalStandard(), 2), 100, 5);
            } else if(rentBreachVo.getBillingCycle() == 2) {
                // 按年
                renoAmount = NumberUtils.divide(NumberUtils.multiply(NumberUtils.multiply(renoAmount, NumberUtils.divide(diffDay, 365, 5), 2), rentBreachVo.getCalStandard(), 2), 100, 5);
            }
        }
        return renoAmount;
    }
}