package com.jef.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jef.canvert.UserCanvert;
import com.jef.context.REContext;
import com.jef.context.REContextManager;
import com.jef.dto.ResultMsgDto;
import com.jef.dto.UserDto;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.ResultMsg;
import com.jef.entity.User;
import com.jef.mq.MessageProduceUtil;
import com.jef.mq.MqConstants;
import com.jef.service.IUserService;
import com.jef.common.utils.MD5Util;
import com.jef.util.REJSONUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 登录控制
 * @author Jef
 * @dater 2018/5/15 19:18
 */
@RequestMapping(value = "/login")
@Controller
public class LoginController {
    private static Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private IUserService userService;

    /**
     * 用户登录请求，账号和密码必填
     * @param userDto
     * @param response
     */
    @RequestMapping("loginOne")
    public void login(UserDto userDto, HttpServletResponse response) {
        User user = UserCanvert.canvertFromDto(userDto);
        if (Objects.nonNull(user)) {
            String passWord = user.getPassword();
            String encodePassWord = MD5Util.encode(passWord);
            User userNew = new User();
            userNew.setName(user.getName());
            userNew.setPassword(encodePassWord);
            userNew.setUserNum(user.getName());
            user = userService.getByNameAndPassWord(userNew);
            if (Objects.nonNull(user)) {
                try {
                    ResultMsgDto resultMsg = new ResultMsgDto();
                    resultMsg.setResultCode(ResultMsg.ResultCodeEnum.SUCCESS_CODE.getResultCode());
                    resultMsg.setResultMsg("登录成功");
                    resultMsg.setResultObject(user);
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println(JSON.toJSON(resultMsg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ResultMsgDto resultMsg = new ResultMsgDto();
                    resultMsg.setResultCode(ResultMsg.ResultCodeEnum.ERROR_CODE.getResultCode());
                    resultMsg.setResultMsg("登录失败");
                    response.setContentType("text/html");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().println(JSON.toJSON(resultMsg));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @RequestMapping(value = "/loginTwo", method = RequestMethod.POST)  //处理login请求
    public ModelAndView loginTwo(String name, String password, ModelAndView mv, HttpSession session, Model model, HttpServletRequest request) {
        logger.info("用户名为 " + name + " 尝试登录");
        logger.debug("用户名为 " + name + " 尝试登录");
        logger.error("用户名为 " + name + " 尝试登录");
        password = MD5Util.encode(password);
        User userNew = new User();
        userNew.setName(name);
        userNew.setPassword(password);
        userNew.setUserNum(name);
        User user = userService.getByNameAndPassWord(userNew);
        if (Objects.nonNull(user)) {
            if (name.equals("Jef")) {
                user.setPermission(1);
            }
            // 登录成功之后把用户信息放入session里
            session.setAttribute("user", user);
            logger.info("用户名为 " + name + " 登录成功");
            session.setAttribute("user", user);
            REContext context = new REContext();
            context.put("loginUser", user);
            REContextManager.setREContext(request.getSession(), context);
            model.addAttribute("userInfo", user);
            // 重定向到homepage页面中
            mv.setViewName("homepage");
        } else {
            logger.error("用户名为 " + name + " 尝试登录失败");
            mv.addObject("message","用户名或者密码错误，请重新输入");
            // 重新设置view视图页面
            mv.setViewName("login");
        }
        Map<String, Object> param = Maps.newHashMap();
        param.put("log", "用户名=" + name + "登录成功");
        MessageProduceUtil.sendQueueMessage(MqConstants.MQ_LOG_SEND, param);
        // 返回视图
        return mv;
    }

    /**
     * 用户登录校验
     * @author Jef
     * @date 2019/5/25
     * @param name 用户名
     * @param password 密码
     * @return com.jef.entity.BaseJSONVo
     */
    @ResponseBody
    @RequestMapping(value = "loginValidate")
    public BaseJSONVo loginValidate(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "password", required = false) String password) {
        logger.info("用户名为 " + name + " 尝试登录");
        logger.debug("用户名为 " + name + " 尝试登录");
        logger.error("用户名为 " + name + " 尝试登录");
        password = MD5Util.encode(password);
        User userNew = new User();
        userNew.setName(name);
        userNew.setPassword(password);
        userNew.setUserNum(name);
        User user = userService.getByNameAndPassWord(userNew);
        return REJSONUtils.success(user, 0, "操作成功");
    }

    /**
     * 用户登出
     * @author Jef
     * @date 2019/7/3
     * @param model
     * @param request
     * @return org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public ModelAndView loginOut(Model model, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        // 返回视图
        return mv;
    }
}

