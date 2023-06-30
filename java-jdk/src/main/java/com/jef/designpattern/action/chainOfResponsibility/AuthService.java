package com.jef.designpattern.action.chainOfResponsibility;

import com.jef.util.PrintUtil;

import com.alibaba.fastjson.JSON;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 审批服务
 *
 * @author Jef
 * @date 2021/12/11
 */
public class AuthService {
    protected static SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Map<String, Date> authMap = new ConcurrentHashMap<String, Date>();

    public static Date queryAuthInfo(String uId, String orderId) {
        return authMap.get(uId.concat(orderId));
    }

    /**
     * 审批通过
     *
     * @param uId     审批人ID
     * @param orderId 订单ID
     */
    public static void auth(String uId, String orderId, String levelUserName, String level, Date billDate, AuthLink authLink) {
        AuthInfo authInfo = new AuthInfo("0000", "单号：", orderId, " 状态：", level, "【", levelUserName, "】完成审批，审批时间：", f.format(new Date()));
        PrintUtil.printf("%s", JSON.toJSONString(authInfo));
        authMap.put(uId.concat(orderId), new Date());
        AuthLink next = authLink.next();
        if (null == next) {
            authInfo = new AuthInfo("0000", "单号：", orderId, " 状态：二级审批人【", levelUserName, "】为最后一个审批通过节点，审批时间：", f.format(new Date()));
            PrintUtil.printf("%s", JSON.toJSONString(authInfo));
            return;
        }
        next.doAuth(uId, orderId, billDate);
    }
}