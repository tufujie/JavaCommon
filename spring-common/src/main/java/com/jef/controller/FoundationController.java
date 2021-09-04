package com.jef.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.jef.constant.BasicConstant;
import com.jef.entity.Foundation;
import com.jef.entity.FoundationBuy;
import com.jef.entity.FoundationEntry;
import com.jef.entity.FoundationShop;
import com.jef.service.IFoundationService;
import com.jef.util.DateTimeUtil;
import com.jef.util.NumberUtils;
import com.jef.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

/**
 * 基金控制器
 * @author Jef
 * @date 2018/9/17 16:21
 */
@Controller
@RequestMapping(value = "/foundation")
public class FoundationController {

    @Autowired
    private IFoundationService foundationService;

    @RequestMapping(value = "/foundation")
    public ModelAndView foundation(ModelAndView mv, Model model, @RequestParam(value = "day", required = false) Integer day) throws ParseException {
        List<Foundation> foundationList = foundationService.getByParams(Maps.newHashMap());
        if (day == null) {
            day = 22;
        }
        for (Foundation foundation : foundationList) {
            foundation.setBuyFlag(foundationService.getByFlag(foundation.getCode(), day));
            foundation.setNewest(foundationService.getNewest(foundation.getCode()));
        }
        int month = day / 22;
        model.addAttribute("month", month);
        model.addAttribute("foundationList", foundationList);
        mv.setViewName("foundation/foundation");
        return mv;
    }

    /**
     * 单个添加
     * @param foundation
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    public String addInfo(Foundation foundation) {
        foundationService.insert(foundation);
        return BasicConstant.SUCCESS;
    }

    /**
     * 单个添加
     * @param foundationEntry
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addInfoEntry", method = RequestMethod.POST)
    public String addInfoEntry(FoundationEntry foundationEntry) {
        foundationService.insertEntry(foundationEntry);
        return BasicConstant.SUCCESS;
    }


    /**
     * 获取预测信息
     * @param mv
     * @param model
     * @return
     */
    @RequestMapping(value = "/getFoundationDetail")
    public ModelAndView getFuture(@RequestParam(value = "code") String code, @RequestParam(value = "day", required = false) Integer day, ModelAndView mv, Model model) throws ParseException {
        if (day == null) {
            day = 22;
        }
        int month = day / 22;
        List<FoundationEntry> foundationEntryList = foundationService.getEntryByParams(ImmutableMap.of("code", code, "day", day));
        Foundation foundation = foundationService.getByCode(code);
        BigDecimal betweenPrice = BigDecimal.ZERO;
        if (foundationEntryList.size() > 0) {
            BigDecimal totalPrice = foundationEntryList.stream().map(FoundationEntry::getUnitPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            betweenPrice = NumberUtils.divide(totalPrice, foundationEntryList.size(), 4);
        }
        FoundationEntry foundationEntryLast = foundationService.getEntryLastByCode(code);
        boolean buyFlag = foundationService.getByFlag(code, day);
        BigDecimal downNum = BigDecimal.ZERO;
        // 平均值和当前值进行对比，当前值小于等于平均值时建议购买
        boolean newest = foundationService.getNewest(code);
        if (foundationEntryLast != null) {
            downNum = NumberUtils.subtract(foundationEntryLast.getUnitPrice(), betweenPrice).setScale(4, BigDecimal.ROUND_HALF_UP);
            if (downNum.compareTo(BigDecimal.ZERO) <= 0) {
                downNum = downNum.negate();
            }
        }
        model.addAttribute("newest", newest);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("buyFlag", buyFlag);
        model.addAttribute("downNum", downNum);
        model.addAttribute("foundation", foundation);
        model.addAttribute("foundationEntryList", foundationEntryList);
        model.addAttribute("betweenPrice", betweenPrice);
        mv.setViewName("foundation/foundationDetail");
        return mv;
    }

    /**
     * 批量添加
     * @param mv
     * @param model
     * @return
     */
    @RequestMapping(value = "/batchAdd")
    public ModelAndView batchAdd(ModelAndView mv, Model model, @RequestParam(value = "code") String code) {
        Foundation foundation = foundationService.getByCode(code);
        model.addAttribute("foundation", foundation);
        mv.setViewName("foundation/batchAdd");
        return mv;
    }

    /**
     * 卖出列表
     * @param mv
     * @param model
     * @return
     */
    @RequestMapping(value = "/getFoundationShop")
    public ModelAndView getFoundationShop(ModelAndView mv, Model model, @RequestParam(value = "code") String code) {
        Foundation foundation = foundationService.getByCode(code);
        List<FoundationShop> foundationShopList = foundationService.getShopByParams(ImmutableMap.of("code", code));
        model.addAttribute("foundationShopList", foundationShopList);
        model.addAttribute("foundation", foundation);
        mv.setViewName("foundation/foundationShop");
        return mv;
    }

    /**
     * 单个添加
     * @param buy
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(FoundationBuy buy) {
        foundationService.insertBuy(buy);
        return BasicConstant.SUCCESS;
    }

    /**
     * 单个添加
     * @param shop
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/shop", method = RequestMethod.POST)
    public String shop(FoundationShop shop) {
        foundationService.insertShop(shop);
        return BasicConstant.SUCCESS;
    }

    /**
     * 获取卖出详情
     * @param mv
     * @param model
     * @return
     */
    @RequestMapping(value = "/getShopDetail")
    public ModelAndView getShopDetail(ModelAndView mv, Model model, @RequestParam(value = "id") String id) throws ParseException {
        FoundationShop foundationShop = foundationService.getShopByID(id);
        Foundation foundation = foundationService.getByCode(foundationShop.getCode());
        List<String> buyDateList = StringUtils.getListFromString(foundationShop.getLinkBuyDate());
        String code = foundation.getCode();
        List<FoundationBuy> foundationBuyList = foundationService.getBuyByParams(ImmutableMap.of("code", code, "buyDateList", buyDateList));
        FoundationEntry foundationEntry = foundationService.getEntryByCodeAndCreate(code, foundationShop.getShopDate());
        BigDecimal shopUnitPrice = foundationEntry.getUnitPrice();
        BigDecimal totalNum = BigDecimal.ZERO;
        String buyDateStart = null;
        for (FoundationBuy foundationBuy : foundationBuyList) {
            FoundationEntry foundationEntryTemp = foundationService.getEntryByCodeAndCreate(code, foundationBuy.getBuyDate());
            BigDecimal sureMoney = NumberUtils.subtract(foundationBuy.getMoney(), foundationBuy.getBuyRatePrice());
            BigDecimal tempNum = NumberUtils.divide(sureMoney, foundationEntryTemp.getUnitPrice(), 2);
            totalNum = NumberUtils.add(totalNum, tempNum);
            buyDateStart = foundationBuy.getBuyDate();
            foundationBuy.setUnitPrice(foundationEntryTemp.getUnitPrice());
            foundationBuy.setNum(tempNum);
            foundationBuy.setSureMoney(sureMoney);
        }
        int totalBuyDate = DateTimeUtil.getDayBetween(DateTimeUtil.parseDate(foundationShop.getShopDate(), DateTimeUtil.NORMAL_DATE_FORMAT),
                DateTimeUtil.parseDate(buyDateStart, DateTimeUtil.NORMAL_DATE_FORMAT));
        BigDecimal totalBuyMoney = foundationBuyList.stream().map(FoundationBuy::getMoney).reduce(BigDecimal.ZERO, NumberUtils::add);
        // 总收益=卖出确认净值 * 持有份额 - 卖出手续费 - 买入总金额
        BigDecimal totalGetMoney = NumberUtils.subtract(NumberUtils.multiply(shopUnitPrice, totalNum, 2), NumberUtils.add(foundationShop.getShopRatePrice(), totalBuyMoney)).
                setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal yearRate = NumberUtils.multiply(NumberUtils.divide(NumberUtils.divide(totalGetMoney, totalBuyMoney, 6), NumberUtils.divide(totalBuyDate, 365, 6), 6), 100, 2);
        model.addAttribute("foundationBuyList", foundationBuyList);
        model.addAttribute("foundation", foundation);
        model.addAttribute("totalNum", totalNum);
        model.addAttribute("shopUnitPrice", shopUnitPrice);
        model.addAttribute("foundationShop", foundationShop);
        model.addAttribute("totalBuyMoney", totalBuyMoney);
        model.addAttribute("totalGetMoney", totalGetMoney);
        model.addAttribute("yearRate", yearRate);
        mv.setViewName("foundation/shopDetail");
        return mv;
    }

}
