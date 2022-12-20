package com.jef.jdk8;

import com.jef.util.LocalDateUtil;
import com.jef.util.PrintUtil;

import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * LocalDate测试
 * @author Jef
 * @date 2020/7/23
 */
public class LocalDateTest {


    // 构造指定的年月日
    @Test
    public void testInitDate() {
        LocalDate localDate = LocalDate.of(2019, 9, 10);
        System.out.println(localDate);
    }


    // 获取今天的日期
    @Test
    public void getCurrentDate(){
        LocalDate today = LocalDate.now();
        System.out.println("Today's Local date : " + today);
        // 这个是作为对比
        Date date = new Date();
        System.out.println(date);
    }

    // 获取年、月、日信息
    @Test
    public void getDetailDate(){
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        int year1 = today.get(ChronoField.YEAR);
        int month1 = today.get(ChronoField.MONTH_OF_YEAR);
        int day1 = today.get(ChronoField.DAY_OF_MONTH);
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfWeek1 = today.get(ChronoField.DAY_OF_WEEK);
        System.out.printf("Year : %d  Month : %d  day : %d dayOfWeek : %d %n", year, month, day, dayOfWeek.getValue());
    }

    // 处理特定日期
    @Test
    public void handleSpecilDate(){
        LocalDate dateOfBirth = LocalDateUtil.getFixedLocalDate();
        System.out.println("The specil date is : " + dateOfBirth);
    }

    // 判断两个日期是否相等
    @Test
    public void compareDate(){
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDateUtil.getFixedLocalDate();
        if (date1.equals(today)) {
            System.out.printf("TODAY %s and DATE1 %s are same date %n", today, date1);
        }
    }

    // 处理周期性的日期
    @Test
    public void cycleDate(){
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDateUtil.getFixedLocalDate();
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
    }

    // 设置当前时间
    @Test
    public void testInitTime(){
        LocalTime localTime = LocalTime.of(13, 51, 10);
        System.out.println(localTime);
    }

    // 获取当前时间
    @Test
    public void getCurrentTime(){
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time);
    }

    // 获取时、分、秒信息
    @Test
    public void getDetailTime() {
        LocalTime localTime = LocalTime.now();
        // 获取小时
        int hour = localTime.getHour();
        int hour1 = localTime.get(ChronoField.HOUR_OF_DAY);
        // 获取分
        int minute = localTime.getMinute();
        int minute1 = localTime.get(ChronoField.MINUTE_OF_HOUR);
        //获取 秒
        int second = localTime.getSecond();
        int second1 = localTime.get(ChronoField.SECOND_OF_MINUTE);
    }


    // 获取年月日时分秒，等于LocalDate+LocalTime
    @Test
    public void testInitDateTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2019, Month.SEPTEMBER, 10, 14, 46, 56);
        LocalDateTime localDateTime2 = LocalDateTime.of(localDate, localTime);
        LocalDateTime localDateTime3 = localDate.atTime(localTime);
        LocalDateTime localDateTime4 = localTime.atDate(localDate);
        // 获取LocalDate
        LocalDate localDate2 = localDateTime.toLocalDate();
        // 获取LocalTime
        LocalTime localTime2 = localDateTime.toLocalTime();
    }

    @Test
    public void testEditDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        // 修改年为2020
        localDateTime = localDateTime.withYear(2020);
        // 修改为2022
        localDateTime = localDateTime.with(ChronoField.YEAR, 2022);
    }

    // 获取秒数
    // 个人觉得如果只是为了获取秒数或者毫秒数，使用System.currentTimeMillis()来得更为方便
    @Test
    public void testInitInstant() {
        Instant instant = Instant.now();
        // 获取秒数
        long currentSecond = instant.getEpochSecond();
        // 获取毫秒数
        long currentMilli = instant.toEpochMilli();
    }

    // 增加小时
    @Test
    public void plusHours(){
        LocalTime time = LocalTime.now();
        // 增加两小时
        LocalTime newTime = time.plusHours(2);
        System.out.println("Time after 2 hours : " +  newTime);
    }

    // 如何计算一天后的日期
    @Test
    public void nextDay() {
        LocalDate today = LocalDate.now();
        LocalDate nextDay = today.plus(1, ChronoUnit.DAYS);
        // 等效
        LocalDate nextDay2 = today.plusDays(1);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 day : " + nextDay);
        System.out.println("Date after 1 day : " + nextDay2);
    }

    // 如何计算一周后的日期
    @Test
    public void nextWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        // 等效
        LocalDate nextWeek2 = today.plusWeeks(1);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);
        System.out.println("Date after 1 week : " + nextWeek2);
    }

    // 如何计算一周后的日期
    @Test
    public void nextMonth() {
        LocalDate today = LocalDate.now();
        LocalDate nextMonth = today.plus(1, ChronoUnit.MONTHS);
        // 等效
        LocalDate nextMonth2 = today.plusMonths(1);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 month : " + nextMonth);
        System.out.println("Date after 1 month : " + nextMonth2);
    }

    // 计算一年前或一年后的日期
    @Test
    public void minusYear() {
        LocalDate today = LocalDate.now();
        // 减少一年
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);
        // 增加一年
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        // 等效
        LocalDate nextYear2 = today.plusYears(1);
        System.out.println("Date after 1 year : " + nextYear);
        System.out.println("Date after 1 year : " + nextYear2);
    }

    @Test
    public void clock(){
        // 根据系统时间返回当前时间并设置为UTC。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);
        // 根据系统时钟区域返回时间
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("DefaultClock : " + defaultClock);
    }

    // 如何用Java判断日期是早于还是晚于另一个日期
    @Test
    public void isBeforeOrIsAfter(){
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }
    }

    // 时区处理
    @Test
    public void getZoneTime(){
        // 设置时区
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localtDateAndTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localtDateAndTime, america );
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }

    // 使用 YearMonth类处理特定的日期
    @Test
    public void checkCardExpiry(){
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
        YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }

    // 检查闰年
    @Test
    public void isLeapYear(){
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year " + today.getYear() + " is Leap year");
        } else {
            System.out.println(today.getYear() + " is not a Leap year");
        }
    }

    // 计算两个日期之间的天数和月数
    @Test
    public void calcDateDays(){
        LocalDate today = LocalDate.now();
        LocalDate java8Release = LocalDate.of(2018, Month.MAY, 14);
        Period periodToNextJavaRelease = Period.between(java8Release, today);
        System.out.printf("between today and Java 8 release %s year %s month %s day  : " , periodToNextJavaRelease.getYears(), periodToNextJavaRelease.getMonths(), periodToNextJavaRelease.getDays());
    }

    // 包含时差信息的日期和时间
    @Test
    public void zoneOffset(){
        LocalDateTime datetime = LocalDateTime.now();
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
    }

    // 获取时间戳
    @Test
    public void getTimestamp() {
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp);
    }

    // 使用预定义的格式化工具去解析或格式化日期
    @Test
    public void parseDate() {
        String dayFormat = "20200723";
        LocalDate formatted = LocalDate.parse(dayFormat, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayFormat, formatted);
        String s1 = formatted.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = formatted.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s1);
        PrintUtil.printSplitLine();
        System.out.println(s2);
        PrintUtil.printSplitLine();
        // 自定义格式化，和SimpleDateFormat相比，DateTimeFormatter是线程安全的
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String s3 = formatted.format(dateTimeFormatter);
        // DateTimeFormatter默认提供了多种格式化方式，如果默认提供的不能满足要求，可以通过DateTimeFormatter的ofPattern方法创建自定义格式化方式
        System.out.println(s3);
    }

    @Test
    public void testDiff() {
        //日期时间差
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.of(2022, Month.OCTOBER, 1);
        Period p = Period.between(birthDate, today);   //  birthDate - today
        System.out.printf("年龄 : %d 年 %d 月 %d 日", p.getYears(), p.getMonths(), p.getDays());
        System.out.println();
        PrintUtil.printSplitLine();

        // 时分秒时间差
        Instant inst1 = Instant.now();
        Instant inst2 = inst1.plus(Duration.ofSeconds(10));
        System.out.println("毫秒差:" + Duration.between(inst1, inst2).toMillis());
        System.out.println("秒差:" + Duration.between(inst1, inst2).getSeconds());

        // ChronoUnit类可用于在单个时间单位内测量一段时间，例如天数或秒
        LocalDate startDate = LocalDate.of(2022, Month.OCTOBER, 1);
        System.out.println("开始时间:" + startDate);
        LocalDate endDate = LocalDate.of(2022, Month.OCTOBER, 16);
        System.out.println("结束时间:" + endDate);
        long daysDiff = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("两天之间的差在天数   : " + daysDiff);//两天之间的差在天数   : 8641
    }

}