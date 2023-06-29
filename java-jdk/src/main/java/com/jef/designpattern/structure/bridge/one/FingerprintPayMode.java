package com.jef.designpattern.structure.bridge.one;

/**
 * 指纹支付
 *
 * @author Jef
 * @date 2021/12/7
 */
public class FingerprintPayMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        System.out.println("指纹支付，风险校验指纹信息");
        return true;
    }
}