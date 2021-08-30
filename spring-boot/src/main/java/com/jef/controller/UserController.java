package com.jef.controller;

import com.jef.entity.User;
import com.jef.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Jef
 * @date 2021/8/30
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping("/getAllUser")
    public String getAllUser(){
        List<User> userList = userService.getAllUser();
        System.out.println("用户数量=" + userList.size());
        return "用户数量=" + userList.size();
    }

}