package com.jef.util;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Jef
 * @date 2018/11/30 16:01
 */
public class MyStringUtilsTest {

    @Test
    public void testLetterNum() {
        String i1 = "abcdef";
        String i2 = "123456";
        System.out.println(StringUtils.letterToNumOne(i1));
        System.out.println(StringUtils.letterToNum(i1));
        System.out.println(StringUtils.numToLetter(i2));
    }

    @Test
    public void testChahange() {
        StringBuilder sb = new StringBuilder();
        BigDecimal a = new BigDecimal("1.0000"), b = new BigDecimal("1"), c = null;
        StringUtils.getAddChangeInfo(sb, "价格1", a, b);
        // 变化
        StringUtils.getAddChangeInfo(sb, "价格2", b, c);
        // 变化
        StringUtils.getAddChangeInfo(sb, "价格3", c, b);
        StringUtils.getAddChangeInfo(sb, "价格4", c, c);
        String d = "test", e = "test",  f = null, g = "test1";
        StringUtils.getAddChangeInfo(sb, "文字1", d, e);
        // 变化
        StringUtils.getAddChangeInfo(sb, "文字2", e, g);
        // 变化
        StringUtils.getAddChangeInfo(sb, "文字3", f, g);
        StringUtils.getAddChangeInfo(sb, "文字4", f, f);
        Date date1 = null, date2 = null, date3 = new Date();
        StringUtils.getAddChangeInfo(sb, "日期1", date1, date2);
        // 变化
        StringUtils.getAddChangeInfo(sb, "日期2", date2, date3);
        // 变化
        StringUtils.getAddChangeInfo(sb, "日期3", date3, date2);
        StringUtils.getAddChangeInfo(sb, "日期4", date3, date3);
        System.out.println(sb.toString());
    }

    @Test
    public void testChahangeV2() {
        StringBuilder sb = new StringBuilder();
        BigDecimal a = null, b = null;
        StringUtils.getAddChangeInfo(sb, "价格1", a, b);
        System.out.println(sb.toString());
    }

    @Test
    public void testListDeal() {
        List<String> aList = StringUtils.getTestStringList();
        List<String> bList = StringUtils.getTestStringListV2();
        List<String> retainList = StringUtils.getAfterRetainAllList(aList, bList);
        List<String> removeList = StringUtils.getAfterRemoveAllList(aList, bList);
        List<String> removeListV2 = StringUtils.getAfterRemoveAllListV2(aList, bList);
        System.out.println(retainList);
        System.out.println(removeList);
        System.out.println(removeListV2);
        System.out.println();
    }

    @Test
    public void testSoutValueAndIDBySplit() {
        List<String> list = Lists.newArrayList();
        list.add("182885d30b544d2e90e58394818bb49d_test1	64a0417542744e019c585c47a959a450");
        list.add("cd63141bea014051ba9cb8870a58e109_test2_test3	65415bda7f554f639ceb28194104dab5");
        list.add("1f3c24c1fcc6433d8596b117bbdd286a_test4,e01b343b80df488f968b7f41bf53a2de_test5,cadc5b53da7843e6b888f3688db13707_test6	65bf470a661a4f42953e3b3e6b8891c4");
        StringUtils.soutValueAndIDBySplit(list);
    }

}
