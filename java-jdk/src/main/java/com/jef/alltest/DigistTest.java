package com.jef.alltest;

import org.springframework.util.DigestUtils;

import java.net.URLDecoder;

public class DigistTest {
    private static final String ACCESSTOKEN = "e76c7bb2b929b137d8ae5d5f2edb6627";
    public static void main(String[] args) {
        String note = "<Comments>Jef的订单备注</Comments>";
        try {
            String digest = getDigist(ACCESSTOKEN, note);
            System.out.println("签名1=" + digest);
            String note1 = new String(note.getBytes("gbk"),"utf-8");
            digest = getDigist(ACCESSTOKEN, note1);
            System.out.println("签名2=" + digest);
            String note2 = URLDecoder.decode(note, "UTF-8");
            digest = getDigist(ACCESSTOKEN, note2);
            System.out.println("签名3=" + digest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 统一加密
     * @param accessToken 授权码
     * @param requestParams 转成String格式的请求参数
     */
    private static String getDigist(String accessToken, String requestParams) {
        return DigestUtils.md5DigestAsHex((accessToken.substring(0, 16) + requestParams + accessToken.substring(16, 32)).getBytes());
    }
}
