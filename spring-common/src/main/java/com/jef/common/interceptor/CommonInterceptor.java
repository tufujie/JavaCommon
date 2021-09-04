package com.jef.common.interceptor;

import com.google.gson.Gson;
import com.jef.entity.User;
import com.jef.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 拦截器
 * @author Jef
 * @create 2018/7/20 14:54
 */
public class CommonInterceptor implements HandlerInterceptor {
    private static final Logger logger = LogManager.getLogger(CommonInterceptor.class);
    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        String url = request.getRequestURL().toString();
        if (url.contains("loginTwo") || url.contains("gotoLogin") || url.contains("/js/")) {
            return true;
        }
        // 获取用户信息
        User user = (User)session.getAttribute("user");
        if (Objects.isNull(user)) {
            user = new User();
            user.setName("Jef");
            user.setPermission(1);
        }
        if (user.getPermission() == null || user.getPermission() == 0) {
            logger.info("用户权限不足");
            String errMsg = "No Permission!";
            this.dealErrorMsg(response, 10000, errMsg);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 通过respon返回ErrorMsg
     * @param response
     * @param code
     * @param errMsg
     * @throws Exception
     */
    private void dealErrorMsg(HttpServletResponse response,Integer code, String errMsg) throws Exception {
        logger.info("code:" + code + " errMsg:" + errMsg);
        response.setStatus(200);
        response.setContentType("application/json;charset=uft-8");
        response.setCharacterEncoding("UTF-8");
        OutputStreamWriter writer =
                new OutputStreamWriter(response.getOutputStream());
        try {
            Map result = new HashMap();
            result.put("code", ""+code);
            result.put("errorMsg", errMsg);
            result.put("success", false);
            String json = (new Gson()).toJson(result);
            writer.write(json);
        } finally {
            writer.flush();
            writer.close();
        }
    }
}
