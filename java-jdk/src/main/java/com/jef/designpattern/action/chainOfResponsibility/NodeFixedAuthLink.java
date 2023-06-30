package com.jef.designpattern.action.chainOfResponsibility;

import java.text.ParseException;
import java.util.Date;

/**
 * 固定审批流设计
 *
 * @author Jef
 * @date 2021/12/11
 */
public class NodeFixedAuthLink extends AuthLink {

    public NodeFixedAuthLink(String levelUserId, String levelUserName, int node) throws ParseException {
        super(levelUserId, levelUserName, node);
    }

    @Override
    public void doAuth(String uId, String orderId, Date authDate) {

    }


}