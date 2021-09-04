package com.jef.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @ResponseBody
    @RequestMapping(value = "/gotoError", method = RequestMethod.GET)
    public ModelAndView gotoRegister(ModelAndView mv) {
        mv.setViewName("tryError");
        return mv;
    }
}
