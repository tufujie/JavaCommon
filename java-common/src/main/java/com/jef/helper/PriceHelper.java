package com.jef.helper;

import com.jef.util.NumberUtils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 价格帮助类
 *
 * @author Jef
 * @date 2018/9/7 11:35
 */
public class PriceHelper {
    // 天数
    private static Integer DAY = 365;
    // 月份数
    private static Integer MONTH = 12;
    // 1代表日的类型，2代表月的类型，3代表年的类型
    private static Integer DAY_TYPE = 1;
    private static Integer MONTH_TYPE = 2;
    private static Integer YEAR_TYPE = 3;

    /**
     * 通过年价格获取其他价格每平米单价
     * @param type 类型
     * @param yearAmount 年价格
     * @param area 面积
     * @param scale 精确度
     * @return 每平米的价格，元/平米/(月or日)
     */
    private BigDecimal getUnitPriceByYear(Integer type, BigDecimal yearAmount, BigDecimal area, Integer scale) {
        if (Objects.equals(type, DAY_TYPE)) {
            return NumberUtils.divide(yearAmount, NumberUtils.multiply(area, DAY, scale), scale);
        } else {
            return NumberUtils.divide(yearAmount, NumberUtils.multiply(area, MONTH, scale), scale);
        }
    }

    /**
     * 通过年价格获取日单价
     * @param yearAmount 年价格，元/年
     * @param area 面积
     * @param scale 精确度
     * @return 每平米每天的价格，元/平米/日
     */
    public BigDecimal getDayUnitPriceByYear(BigDecimal yearAmount, BigDecimal area, Integer scale) {
        return getUnitPriceByYear(DAY_TYPE, yearAmount, area, scale);
    }

    /**
     * 通过年价格获取月单价
     * @param yearAmount 年价格，元/年
     * @param area 面积
     * @param scale 精确度
     * @return 每平米每天的价格，元/平米/月
     */
    public BigDecimal getMonthUnitPriceByYear(BigDecimal yearAmount, BigDecimal area, Integer scale) {
        return getUnitPriceByYear(MONTH_TYPE, yearAmount, area, scale);
    }

    /**
     * 通过月单价获取其它单价
     * @param type 类型
     * @param monthUnitAmount 月单价，元/平米/月
     * @param scale 精确度
     * @return 每(日or年)的价格，元/日，元/年
     */
    private BigDecimal getPriceByMonthUnit(Integer type, BigDecimal monthUnitAmount, BigDecimal area,  Integer scale) {
        if (Objects.equals(type, YEAR_TYPE)) {
            return NumberUtils.multiply(getYearUnitPriceByMonthUnit(monthUnitAmount, scale), area, scale);
        } else {
            return NumberUtils.multiply(getDayUnitPriceByMonthUnit(monthUnitAmount, scale), area, scale);
        }
    }

    /**
     * 通过月单价获取日单价
     * @param monthUnitAmount 月单价，元/平米/月
     * @param scale 精确度
     * @return 每日的价格，元/日
     */
    public BigDecimal getDayPriceByMonthUnit(BigDecimal monthUnitAmount, BigDecimal area, Integer scale) {
        return getPriceByMonthUnit(DAY_TYPE, monthUnitAmount, area, scale);
    }

    /**
     * 通过月单价获取年单价
     * @param monthUnitAmount 月单价，元/平米/月
     * @param scale 精确度
     * @return 每年的价格，元/年
     */
    public BigDecimal getYearPriceByMonthUnit(BigDecimal monthUnitAmount, BigDecimal area, Integer scale) {
        return getPriceByMonthUnit(YEAR_TYPE, monthUnitAmount, area, scale);
    }

    /**
     * 通过月单价获取日单价
     * @param monthUnitAmount 月单价，元/平米/月
     * @param scale 精确度
     * @return 每日每平米的价格，元/平米/日
     */
    public BigDecimal getDayUnitPriceByMonthUnit(BigDecimal monthUnitAmount, Integer scale) {
        return getPriceByMonth(DAY_TYPE, monthUnitAmount, scale);
    }

    /**
     * 通过月单价获取年单价
     * @param monthUnitAmount 月单价，元/平米/月
     * @param scale 精确度
     * @return 每年每平米的价格，元/平米/年
     */
    public BigDecimal getYearUnitPriceByMonthUnit(BigDecimal monthUnitAmount, Integer scale) {
        return getPriceByMonth(YEAR_TYPE, monthUnitAmount, scale);
    }


    /**
     * 通过日单价获取其它价格
     * @param type 类型
     * @param dayUnitAmount 日单价，元/平米/天
     * @param area 面积
     * @param scale 精确度
     * @return 每(月or年)的价格，元/(月or年)
     */
    private BigDecimal getPriceByDayUnit(Integer type, BigDecimal dayUnitAmount, BigDecimal area, Integer scale) {
        if (Objects.equals(type, YEAR_TYPE)) {
            return NumberUtils.multiply(dayUnitAmount, NumberUtils.multiply(area, DAY, scale), scale);
        } else {
            return NumberUtils.divide(getPriceByDayUnit(YEAR_TYPE, dayUnitAmount, area, scale), MONTH, scale);
        }
    }

    /**
     * 通过日单价获取月价格
     * @param dayUnitAmount 日单价，元/平米/天
     * @param area 面积
     * @param scale 精确度
     * @return 每月的价格，元/月
     */
    public BigDecimal getMonthPriceByDayUnit(BigDecimal dayUnitAmount, BigDecimal area, Integer scale) {
        return getPriceByDayUnit(MONTH_TYPE, dayUnitAmount, area, scale);
    }

    /**
     * 通过日单价获取年价格
     * @param dayUnitAmount 日单价，元/平米/天
     * @param area 面积
     * @param scale 精确度
     * @return 每年的价格，元/年
     */
    public BigDecimal getYearPriceByDayUnit(BigDecimal dayUnitAmount, BigDecimal area, Integer scale) {
        return getPriceByDayUnit(YEAR_TYPE, dayUnitAmount, area, scale);
    }

    /**
     * 通过月价格获取其它价格
     * @param monthAmount 月价格，元/月
     * @param scale 精确度
     * @return 每(年or天)的价格，元/(年or天)
     */
    private BigDecimal getPriceByMonth(Integer type, BigDecimal monthAmount, Integer scale) {
        if (Objects.equals(type, YEAR_TYPE)) {
            return NumberUtils.multiply(monthAmount, MONTH, scale);
        } else {
            return NumberUtils.divide(getPriceByMonth(YEAR_TYPE, monthAmount, scale), DAY, scale);
        }
    }

    /**
     * 通过月价格获取日价格
     * @param monthAmount 月价格，元/月
     * @param scale 精确度
     * @return 每天的价格，元/天
     */
    public BigDecimal getDayPriceByMonth(BigDecimal monthAmount, Integer scale) {
        return getPriceByMonth(DAY_TYPE, monthAmount, scale);
    }

    /**
     * 通过月价格获取年价格
     * @param monthAmount 月价格，元/月
     * @param scale 精确度
     * @return 每年的价格，元/年
     */
    public BigDecimal getYearPriceByMonth(BigDecimal monthAmount, Integer scale) {
        return getPriceByMonth(YEAR_TYPE, monthAmount, scale);
    }

    /**
     * 通过年价格获取其它价格
     * @param yearAmount 年价格，元/年
     * @param scale 精确度
     * @return 每(月or天)的价格，元/(月or天)
     */
    private BigDecimal getPriceByYear(Integer type, BigDecimal yearAmount, Integer scale) {
        if (Objects.equals(type, MONTH_TYPE)) {
            return NumberUtils.divide(yearAmount, MONTH, scale);
        } else {
            return NumberUtils.divide(yearAmount, DAY, scale);
        }
    }

    /**
     * 通过年价格获取日价格
     * @param yearAmount 年价格，元/年
     * @param scale 精确度
     * @return 每天的价格，元/天
     */
    public BigDecimal getDayPriceByYear(BigDecimal yearAmount, Integer scale) {
        return getPriceByYear(DAY_TYPE, yearAmount, scale);
    }

    /**
     * 通过年价格获取月价格
     * @param yearAmount 年价格，元/年
     * @param scale 精确度
     * @return 每月的价格，元/月
     */
    public BigDecimal getMonthPriceByYear(BigDecimal yearAmount, Integer scale) {
        return getPriceByYear(MONTH_TYPE, yearAmount, scale);
    }

}
