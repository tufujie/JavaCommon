package com.jef.util;

import org.junit.Test;

import java.util.Map;

/**
 * @author Jef
 * @date 2020/6/13
 */
public class LocationUtilTest {

    @Test
    public void testGet() {
        System.out.println(LocationUtil.getAddressSplit("深圳市宝安区新安街道新安湖社区宝城前进一路宝前巷46号经发大厦"));
    }

    @Test
    public void getCounty() {
        Map<String, String> addressMap = LocationUtil.getAddressSplit("深圳市宝安区新安街道新安湖社区宝城前进一路宝前巷46号经发大厦");
        System.out.println(LocationUtil.getCounty(addressMap));
    }

    @Test
    public void getTown() {
        Map<String, String> addressMap = LocationUtil.getAddressSplit("深圳市宝安区新安街道新安湖社区宝城前进一路宝前巷46号经发大厦");
        System.out.println(LocationUtil.getTown(addressMap));
    }

}