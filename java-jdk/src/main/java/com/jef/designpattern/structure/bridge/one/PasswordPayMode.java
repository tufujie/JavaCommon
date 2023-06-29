package com.jef.designpattern.structure.bridge.one;

/**
 * 密码支付
 *
 * @author Jef
 * @date 2021/12/7
 */
public class PasswordPayMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        System.out.println("密码支付，风险校验环境安全");
        return true;
    }
}