package com.jef.controller;

import com.google.common.collect.Maps;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.User;
import com.jef.service.IUserService;
import com.jef.util.NumberUtils;
import com.jef.util.REJSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 切面controller
 *
 * @author Jef
 * @date: 2021/4/15 19:22
 */
@Controller
@RequestMapping(value = "/aspect")
public class AspectController {

    @Autowired
    private IUserService userService;

    /**
     * 其他功能的跳转
     * @param mv
     * @return
     */
    @RequestMapping(value = "/aspect")
    public ModelAndView otherIntroduce(ModelAndView mv) {
        mv.setViewName("aspect/aspect");
        return mv;
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.GET)
    @ResponseBody
    public BaseJSONVo getRentPlans(
            @RequestParam("rowCount") String rowCount,
            @RequestParam("current") String current,
            @RequestParam(value = "searchPhrase", required = false) String searchPhrase) throws Exception {
        int startPageNum = NumberUtils.getNumber(current, 1).intValue();
        int pageCountNum = NumberUtils.getNumber(rowCount, 10).intValue();
        Map<String, Object> queryMap = Maps.newHashMap();
        queryMap.put("searchPhrase", searchPhrase);
        List<User> userList = userService.query(queryMap, startPageNum, pageCountNum);
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("rows", userList);
        resultMap.put("current", startPageNum);
        resultMap.put("rowCount", pageCountNum);
        resultMap.put("total", userList.size());
        return REJSONUtils.success(resultMap, 0, "操作成功");
    }

    @RequestMapping(value = "/exceptionTest", method = RequestMethod.GET)
    @ResponseBody
    public BaseJSONVo exceptionTest() {
        int a = 2;
        int b = 0;
        System.out.println(a / b);
        return REJSONUtils.success(true, 0, "操作成功");
    }

}