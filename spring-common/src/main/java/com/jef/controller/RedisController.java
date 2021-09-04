package com.jef.controller;

import com.jef.common.utils.BasicJspUtil;
import com.jef.constant.BasicConstant;
import com.jef.dao.IUserDao;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.User;
import com.jef.property.cache.UserCache;
import com.jef.redis.cache.ObjectCache;
import com.jef.service.IUserService;
import com.jef.util.REJSONUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * 测试redis的controller
 * @author Jef
 * @date 2018/11/30 10:25
 */
@Controller
@RequestMapping(value = "/redis")
public class RedisController {
    private static Logger logger = LogManager.getLogger(RedisController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/redisIntroduce")
    public ModelAndView introduce(ModelAndView mv) {
        mv.setViewName("redis/redisIntroduce");
        return mv;
    }

    /**
     * 缓存object
     */
    @ResponseBody
    @RequestMapping(value = "/setObjectCache")
    public BaseJSONVo setObjectCache(@RequestParam(value = "userId", required = false) Long userID) throws Exception {
        if (userID == null) {
            userID = 1L;
        }
        User user = userService.getUserByID(userID);
        return REJSONUtils.success(user, 0, "操作成功");
    }


    /**
     * 取出object
     */
    @ResponseBody
    @RequestMapping(value = "/getObjectCache")
    public BaseJSONVo getObjectCache(@RequestParam(value = "userId", required = false) Long userID) throws Exception {
        if (userID == null) {
            userID = 1L;
        }
        User cacheUser = (User)ObjectCache.getCache(User.OBJECT_KEY, String.valueOf(userID));
        return REJSONUtils.success(cacheUser, 0, "操作成功");
    }

    /**
     * 缓存string和取出string
     */
    @RequestMapping(value = "/string")
    public ModelAndView string(ModelAndView mv) throws Exception {
        String phone = BasicConstant.EMAIL;
        ObjectCache.setCache(phone, BasicConstant.USER_NAME, BasicConstant.FIFTY_MINUTE);
        String userName = ObjectCache.getCache(phone);
        logger.info("设置的value是否等于获取的=" + Objects.equals(BasicConstant.USER_NAME, userName));
        return BasicJspUtil.getBasicView();
    }

}
