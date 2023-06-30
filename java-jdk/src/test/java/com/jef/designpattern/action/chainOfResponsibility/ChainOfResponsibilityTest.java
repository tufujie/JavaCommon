package com.jef.designpattern.action.chainOfResponsibility;

import com.jef.constant.BasicConstant;
import com.jef.util.PrintUtil;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 责任链测试
 *
 * @author Jef
 * @date 2021/12/11
 */
public class ChainOfResponsibilityTest {

    @Test
    public void testDuty() throws ParseException {
        AuthLink authLink = new Level3AuthLink("0000103", "王工").appendNext(new Level2AuthLink("0000002", "邓经理 ").appendNext(new Level1AuthLink("0000001", "涂总")));
        // 订单ID
        String orderId = BasicConstant.FIRST_ORDER_ID;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 模拟三级审批人审批
        Date billDate = f.parse("2021-12-18 09:00:00");
        authLink.doAuth(BasicConstant.FIRST_USER_NUMBER, orderId, billDate);
        PrintUtil.printf("单号：%s，审批完成，审批时间：%s", orderId, f.format(new Date()));
        PrintUtil.printSplitLine();

        // 模拟三、二级审批人审批
        orderId = BasicConstant.SECOND_ORDER_ID;
        authLink = new Level3AuthLink("0000103", "王工").appendNext(new Level2AuthLink("0000002", "邓经理 ").appendNext(new Level1AuthLink("0000001", "涂总")));
        billDate = f.parse("2021-06-01 09:00:00");
        authLink.doAuth(BasicConstant.FIRST_USER_NUMBER, orderId, billDate);
        PrintUtil.printf("单号：%s，审批完成，审批时间：%s", orderId, f.format(new Date()));
        PrintUtil.printSplitLine();

        // 模拟三、二、一级审批人审批
        // 模拟三、二级审批人审批
        orderId = BasicConstant.THIRD_ORDER_ID;
        authLink = new Level3AuthLink("0000103", "王工").appendNext(new Level2AuthLink("0000002", "邓经理 ").appendNext(new Level1AuthLink("0000001", "涂总")));
        billDate = f.parse("2021-06-18 09:00:00");
        authLink.doAuth(BasicConstant.FIRST_USER_NUMBER, orderId, billDate);
        PrintUtil.printf("单号：%s，审批完成，审批时间：%s", orderId, f.format(new Date()));
        PrintUtil.printSplitLine();
    }

    /**
     * 固定审批人审批流测试
     *
     * @author Jef
     * @date 2021/12/11
     */
    @Test
    public void testFixedNodeByDuty() throws ParseException {
        NodeFixedAuthLink authLinkTwo = new NodeFixedAuthLink("0000103", "王工", 2);
        NodeFixedAuthLink authLinkOne = new NodeFixedAuthLink("0000002", "邓经理", 1);
        NodeFixedAuthLink authLinkThree = new NodeFixedAuthLink("0000001", "涂总", 0);
        // 模拟数据库设计好的固定审批流，不管取出来的顺序有没有排序
        List<NodeFixedAuthLink> authLinkList = Lists.newArrayList();
        authLinkList.add(authLinkOne);
        authLinkList.add(authLinkTwo);
        authLinkList.add(authLinkThree);
        // 然后找到节点关系
        Level3AuthLink level3AuthLink = null;
        Level2AuthLink level2AuthLink = null;
        Level1AuthLink level1AuthLink = null;
        for (NodeFixedAuthLink authLink : authLinkList) {
            if (authLink.getNode() == 2) {
                level3AuthLink = new Level3AuthLink(authLink.levelUserId, authLink.levelUserName);
            } else if (authLink.getNode() == 1) {
                level2AuthLink = new Level2AuthLink(authLink.levelUserId, authLink.levelUserName);
            } else if (authLink.getNode() == 0) {
                level1AuthLink = new Level1AuthLink(authLink.levelUserId, authLink.levelUserName);
            }
        }
        AuthLink authLink = level3AuthLink.appendNext(level2AuthLink.appendNext(level1AuthLink));
        // 订单ID
        String orderId = BasicConstant.FIRST_ORDER_ID;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 模拟三、二、一级审批人审批
        // 模拟三、二级审批人审批
        Date billDate = f.parse("2021-06-18 09:00:00");
        authLink.doAuth(BasicConstant.FIRST_USER_NUMBER, orderId, billDate);
        PrintUtil.printf("单号：%s，审批完成，审批时间：%s", orderId, f.format(new Date()));
        PrintUtil.printSplitLine();
    }

}