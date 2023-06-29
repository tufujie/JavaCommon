package com.jef.designpattern.structure.bridge.one;

import java.math.BigDecimal;

/**
 * 支付宝支付
 *
 * @author Jef
 * @date 2021/12/7
 */
public class ZfbPay extends Pay {

    public ZfbPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transferAmount(String uId, String tradeId, BigDecimal amount) {
        System.out.printf("模拟支付宝渠道支付划账开始。uId=%s tradeId=%s, amount=" + amount, uId, tradeId);
        System.out.println();
        boolean security = payMode.security(uId);
        System.out.printf("模拟支付宝渠道支付风险校验。uId=%s tradeId=%s, security=" + security, uId, tradeId);
        System.out.println();
        if (!security) {
            System.out.printf("模拟支付宝渠道支付划账拦截。uId=%s tradeId=%s, amount=" + amount, uId, tradeId);
            System.out.println();
            return "0001";
        }
        System.out.printf("模拟支付宝渠道支付划账成功。uId=%s tradeId=%s, amount=" + amount, uId, tradeId);
        System.out.println();
        return "0000";
    }
}