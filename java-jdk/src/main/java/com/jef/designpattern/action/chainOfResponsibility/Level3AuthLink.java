package com.jef.designpattern.action.chainOfResponsibility;

import com.jef.util.PrintUtil;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.util.Date;

/**
 * 一级审批
 *
 * @author Jef
 * @date 2021/12/11
 */
public class Level3AuthLink extends AuthLink {

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    public void doAuth(String uId, String orderId, Date billDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            AuthInfo authInfo = new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批人【", levelUserName, "】审批");
            PrintUtil.printf("%s", JSON.toJSONString(authInfo));
            AuthService.auth(levelUserId, orderId, levelUserName, "三级审批人", billDate, this);
        }
    }
}