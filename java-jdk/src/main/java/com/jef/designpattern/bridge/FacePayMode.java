package com.jef.designpattern.bridge;

/**
 * 刷脸支付
 *
 * @author Jef
 * @date 2021/12/7
 */
public class FacePayMode implements IPayMode {

    @Override
    public boolean security(String uId) {
        System.out.println("人脸支付，风险校验脸部识别");
        return true;
    }
}