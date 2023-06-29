package com.jef.designpattern.structure.bridge.one;

import java.math.BigDecimal;

/**
 * 支付接口
 * 抽象部分与实现部分分离，把多种可匹配的使用进行组合
 *
 * @author Jef
 * @date 2021/12/7
 */
public abstract class Pay {

    protected IPayMode payMode;

    /**
     * A类中包含有B类接口
     *
     * @param payMode 支付方式
     * @author Jef
     * @date 2021/12/7
     */
    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    /**
     * 转款
     *
     * @param uId     用户ID
     * @param tradeId 交易ID
     * @param amount  交易金额
     * @return java.lang.String
     * @author Jef
     * @date 2021/12/7
     */
    public abstract String transferAmount(String uId, String tradeId, BigDecimal amount);

}