package com.jef.service.impl;

import com.google.common.collect.ImmutableMap;
import com.jef.dao.IFoundationDao;
import com.jef.dao.IFoundationEntryDao;
import com.jef.entity.Foundation;
import com.jef.entity.FoundationBuy;
import com.jef.entity.FoundationEntry;
import com.jef.entity.FoundationShop;
import com.jef.service.IFoundationService;
import com.jef.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2019/7/6
 */
@Service(value = "foundationService")
public class IFoundationServiceImpl implements IFoundationService {
    @Autowired
    private IFoundationDao foundationDao;
    @Autowired
    private IFoundationEntryDao foundationEntryDao;

    @Override
    public void insert(Foundation foundation) {
        foundationDao.insert(foundation);
    }

    @Override
    public List<Foundation> getByParams(Map<String, Object> requestParams) {
        return foundationDao.getByParams(requestParams);
    }

    @Override
    public void insertEntry(FoundationEntry foundationEntry) {
        List<FoundationEntry> foundationEntryList = foundationEntryDao.getByParams(ImmutableMap.of("code", foundationEntry.getCode(), "createDate",
                foundationEntry.getCreateDate(), "day", 1));
        if (foundationEntryList != null && foundationEntryList.size() > 0) {
            return;
        }
        foundationEntryDao.insert(foundationEntry);
    }

    @Override
    public List<FoundationEntry> getEntryByParams(Map<String, Object> requestParams) {
        return foundationEntryDao.getByParams(requestParams);
    }

    @Override
    public FoundationEntry getEntryLastByCode(String code) {
        return foundationEntryDao.getEntryLastByCode(code);
    }

    @Override
    public FoundationEntry getEntryByCodeAndCreate(String code, String createDate) {
        return foundationEntryDao.getEntryLastByCodeAndCreate(code, createDate);
    }

    @Override
    public Foundation getByCode(String code) {
        return foundationDao.selectByPrimaryKey(code);
    }

    @Override
    public boolean getByFlag(String code, Integer day) {
        if (day == null) {
            day = 66;
        }
        List<FoundationEntry> foundationEntryList = foundationEntryDao.getByParams(ImmutableMap.of("code", code, "day", day));
        BigDecimal betweenPrice = BigDecimal.ZERO;
        if (foundationEntryList.size() > 0) {
            BigDecimal totalPrice = foundationEntryList.stream().map(FoundationEntry::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            betweenPrice = totalPrice.divide(new BigDecimal(foundationEntryList.size()), 4, BigDecimal.ROUND_HALF_UP);
        }
        FoundationEntry foundationEntryLast = foundationEntryDao.getEntryLastByCode(code);
        boolean buyFlag = false;
        // 平均值和当前值进行对比，当前值小于等于平均值时建议购买
        if (foundationEntryLast != null) {
            if (foundationEntryLast.getUnitPrice().subtract(betweenPrice).setScale(4, BigDecimal.ROUND_HALF_UP).compareTo(BigDecimal.ZERO) <= 0) {
                buyFlag = true;
            }
        }

        return buyFlag;
    }

    @Override
    public boolean getNewest(String code) throws ParseException {
        FoundationEntry foundationEntryLast = foundationEntryDao.getEntryLastByCode(code);
        if (foundationEntryLast != null) {
            Date now = new Date();
            Date lastDate = DateTimeUtil.parseDate(foundationEntryLast.getCreateDate(), DateTimeUtil.NORMAL_DATE_FORMAT);
            String nowStr = DateTimeUtil.formatDate(now);
            Date nowDate = DateTimeUtil.parseDate(nowStr, DateTimeUtil.NORMAL_DATE_FORMAT);
            int dayOfWeek = DateTimeUtil.getDayOfWeek(now);
            if (dayOfWeek == 7 || dayOfWeek == 1) {
                Date compareDate = DateTimeUtil.addDays(nowDate, dayOfWeek == 7 ? -1 : -2);
                return lastDate.compareTo(compareDate) == 0;
            }
            if (now.getHours() > 15) {
                return lastDate.compareTo(nowDate) == 0;
            } else {
                Date yesterday = DateTimeUtil.getYesterday(nowDate);
                return lastDate.compareTo(yesterday) == 0;
            }
        }
        return false;
    }

    @Override
    public List<FoundationShop> getShopByParams(Map<String, Object> requestParams) {
        return foundationDao.getShopByParams(requestParams);
    }

    @Override
    public FoundationShop getShopByID(String id) {
        return foundationDao.getShopByID(id);
    }

    @Override
    public List<FoundationBuy> getBuyByParams(Map<String, Object> requestParams) {
        return foundationDao.getBuyByParams(requestParams);
    }

    @Override
    public FoundationBuy getBuyByID(String id) {
        return foundationDao.getBuyByID(id);
    }

    @Override
    public void insertBuy(FoundationBuy buy) {
        foundationDao.insertBuy(buy);
    }

    @Override
    public void insertShop(FoundationShop shop) {
        foundationDao.insertShop(shop);
    }
}