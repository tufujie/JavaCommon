package com.jef.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jef.constant.BasicConstant;
import com.jef.context.REContext;
import com.jef.context.REContextManager;
import com.jef.entity.Config;
import com.jef.entity.User;
import com.jef.service.IUserService;
import com.jef.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jef
 * @date 2018/8/16 19:48
 */
@Controller
@RequestMapping(value = "/model")
public class ModelController {
    private static final Logger logger = LogManager.getLogger(ModelController.class);

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/modelJsp")
    public ModelAndView introduce(ModelAndView mv, Model model) {
        mv.setViewName("modelJsp");
        return mv;
    }

    /**
     * jstl语法
     * @param mv
     * @return
     */
    @RequestMapping(value = "/jstl")
    public ModelAndView jstl(ModelAndView mv, Model model) {
        REContext context = REContextManager.getREContext();
        User loginUser = (User) context.get("loginUser");
        logger.info("登录用户名=" + loginUser.getName());
        String adminName = BasicConstant.USER_NAME;
        User user = userService.getByName(adminName);
        if (Objects.nonNull(user)) {
            mv.addObject("admin", Boolean.TRUE);
        }
        // 1,2,6,8可行
        mv.addObject("phone", user.getPhone());
        mv.addObject("phone2", StringUtils.replaceAll(user.getPhone(), "\n", "&#10;", true));
        mv.addObject("phone3", StringUtils.replaceAll(user.getPhone(), "\n", "\r\n", true));
        mv.addObject("phone4", StringUtils.replaceAll(user.getPhone(), "\n", "\\r\\n", true));
        mv.addObject("phone5", StringUtils.replaceAll(user.getPhone(), "\n", "&lt;br&gt;", true));
        mv.addObject("phone6", StringUtils.replaceAll(user.getPhone(), "\n", "&#13;", true));
        mv.addObject("phone7", StringUtils.replaceAll(user.getPhone(), "\n", "<br/>", true));
        String phone8 = StringUtils.replaceAll(user.getPhone(), "\n", "<br/>", true);
        phone8 = StringUtils.replaceAll(phone8, " ", java.util.regex.Matcher.quoteReplacement("&nbsp;"), false);
        mv.addObject("phone8", phone8);
        // 单个属性
        mv.addObject("status", "2");
        List<User> userList = userService.getAllUser();
        // 单个对象
        model.addAttribute("userOne", null);
        model.addAttribute("userOneSecond", "");
        model.addAttribute("userOneThird", "test");
        User userTwo = new User();
        userTwo.setName("");
        model.addAttribute("userTwo", userTwo);
        // 对象集合
        mv.addObject("userList", userList);
        mv.addObject("now", new Date());
        mv.addObject("numberBit", 5);
        // 浮点数输出，会去掉末尾的0
        mv.addObject("money", 120000.23190);
        mv.addObject("moneyFloat", 120000.23190F);
        // 要想保留BigDecimal小数点后末尾0，方式1：值的类型是字符串，方式2：前台js初始化值的时候使用toFixed()，方式3：BigDecimal设置精确度
        mv.addObject("moneyStr", "120000.23190");
        mv.addObject("moneyTwo", new BigDecimal(120000.23190).setScale(5, BigDecimal.ROUND_HALF_UP));
        // 保留3位，会进行五入
        mv.addObject("moneyThree", new BigDecimal(120000.23190).setScale(3, BigDecimal.ROUND_HALF_UP));
        mv.addObject("moneyFour", new BigDecimal(120000.23190).setScale(2, BigDecimal.ROUND_HALF_UP));
        // 简单集合
        List<String> list = Lists.newArrayList();
        list.add("listValue1");
        list.add("listValue2");
        model.addAttribute("listTest", list);
        // 简单map
        Map map = Maps.newHashMap();
        map.put("keyName1", "value1");
        map.put("keyName2", "value1");
        model.addAttribute("mapTest", map);
        // 复杂map
        List<Map<String, Object>> listMap = Lists.newArrayList();
        for (User userVo : userList) {
            Map mapTwo = Maps.newHashMap();
            mapTwo.put("user", userVo);
            listMap.add(mapTwo);
        }
        model.addAttribute("mapTestTwo", listMap);
        mv.setViewName("jstl");
        return mv;
    }

    /**
     * config数据库配置表展示形式
     * @param mv
     * @return
     */
    @RequestMapping(value = "/config")
    public ModelAndView config(ModelAndView mv, Model model) {
        // 实际会从数据库读取，order by level
        List<Config> configs = Lists.newArrayList();
        Config config = new Config();
        config.setLevel(1);
        config.setName("A级");
        configs.add(config);
        config = new Config();
        config.setLevel(2);
        config.setName("B级");
        configs.add(config);
        config = new Config();
        config.setLevel(3);
        config.setName("C级");
        configs.add(config);
        model.addAttribute("configs", configs);
        mv.setViewName("config");
        return mv;
    }
}
