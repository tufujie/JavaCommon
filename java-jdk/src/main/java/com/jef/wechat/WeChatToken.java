package com.jef.wechat;

import com.jef.util.HttpClientUtil;

import com.jef.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @date 2022/9/23
 */
public class WeChatToken {

    private static final String APPID = "wx34951**77d542**";
    private static final String APPSECRET = "65717ef**46d13f3c407**c0c34";

    public static void main(String[] args) {
        //通过网关获取微信token
        Map<String, String> params = new HashMap<String, String>();
        params.put("appId", APPID);
        params.put("appSecret", APPSECRET);

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + APPSECRET;
        String tokenJson = HttpClientUtil.sendGetRequest(url);
        System.out.println(tokenJson);
        User user = new User();
    }


}