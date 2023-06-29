package com.jef.designpattern.structure.bridge;

import com.jef.designpattern.structure.bridge.one.FacePayMode;
import com.jef.designpattern.structure.bridge.one.FingerprintPayMode;
import com.jef.designpattern.structure.bridge.one.Pay;
import com.jef.designpattern.structure.bridge.one.WxPay;
import com.jef.designpattern.structure.bridge.one.ZfbPay;
import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author Jef
 * @date 2021/12/7
 */
public class BridgeTest {

    @Test
    public void testBridge() {
        Pay wxPay = new WxPay(new FacePayMode());
        wxPay.transferAmount("weixin_000001", "000001", new BigDecimal(100));
        PrintUtil.printSplitLine();

        Pay zfbPay = new ZfbPay(new FingerprintPayMode());
        zfbPay.transferAmount("zhifubao_000001", "000002", new BigDecimal(200));
    }

}