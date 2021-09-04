package com.jef.controller;

import com.jef.common.utils.BasicJspUtil;
import com.jef.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

/**
 * 测试控制器
 * @author Jef
 * @create 2018/7/20 13:58
 */
@Controller
@RequestMapping("/myTest")
public class TestController {

    /**
     * 测试页面跳转
     * @param mv
     * @return
     */
    @RequestMapping(value = "test")
    public ModelAndView otherIntroduce(ModelAndView mv) {
        mv.setViewName("test/test");
        return mv;
    }

    @RequestMapping ("sayHello")
    public ModelAndView sayHello(@ModelAttribute("hello") String hello,
                         @ModelAttribute User user,
                         @ModelAttribute("num") int num) throws IOException {
        System.out.println("Say " + hello + " to " + user.getName() + " " + num + " 次");
        return BasicJspUtil.getBasicView();
    }

}
