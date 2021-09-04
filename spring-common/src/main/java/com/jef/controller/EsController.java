package com.jef.controller;

import com.jef.entity.BaseJSONVo;
import com.jef.entity.User;
import com.jef.service.IEsService;
import com.jef.service.IUserService;
import com.jef.util.REJSONUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试es的controller
 * @author Jef
 * @date 2018/11/30 10:25
 */
@Controller
@RequestMapping(value = "/es")
public class EsController {
    private static Logger logger = LogManager.getLogger(EsController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private IEsService esService;

    @RequestMapping(value = "/es")
    public ModelAndView introduce(ModelAndView mv) {
        mv.setViewName("es/es");
        return mv;
    }

    /**
     * 缓存object
     */
    @ResponseBody
    @RequestMapping(value = "/setEs")
    public BaseJSONVo setObjectCache(@RequestParam(value = "userId", required = false) Long userID) throws Exception {
        if (userID == null) {
            userID = 1L;
        }
        User user = userService.getUserByID(userID);
        esService.getClient();
        return REJSONUtils.success(user, 0, "操作成功");
    }

}
