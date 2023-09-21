package com.jef.util;

import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * @author Jef
 * @date 2019/8/23
 */
public class DateTimeUtilTest {
    private static final  String START_DATE_STR = "2019-01-01";
    private static final  String END_DATE_STR = "2019-12-31";
    private static final  String START_MD_STR = "01-01";
    private static final  String END_MD_STR = "12-31";
    private static final  String NOT_RIGHT_STR = "2019-01-32";

    @Test
    public void testGetMonthBetween() throws Exception {
        List<String> list = DateTimeUtil.getMonthBetween(START_MD_STR, END_MD_STR);
        System.out.println(list);
    }

    @Test
    public void testCheckTimeNotCorrect() {
        Date date = DateTimeUtil.getDateFromStrUnknownFormat(START_DATE_STR);
        System.out.println(date);
        date = DateTimeUtil.getDateFromStrUnknownFormat(NOT_RIGHT_STR);
        System.out.println(date);
    }

    @Test
    public void testGetFirstDayOfWeek() {
        Date firstDate = DateTimeUtil.getFirstDayOfWeek(new Date(), 1);
        System.out.println(DateTimeUtil.formatDate(firstDate));
        firstDate = DateTimeUtil.getFirstDayOfWeek(new Date(), 2);
        System.out.println(DateTimeUtil.formatDate(firstDate));
    }

    @Test
    public void testLastDayOfWeek() {
        Date lastDate = DateTimeUtil.getLastDayOfWeek(new Date(), 1);
        System.out.println(DateTimeUtil.formatDate(lastDate));
    }

    @Test
    public void testGetToday() {
        Date today = DateTimeUtil.getToday();
        System.out.println(DateTimeUtil.formatDate(today, DateTimeUtil.DEFAULT_DATE_FORMAT));
    }
}