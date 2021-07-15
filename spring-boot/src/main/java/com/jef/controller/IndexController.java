package com.jef.controller;

/**
 * @author Jef
 * @date 2019/6/29
 */
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.jef.entity.User;
import com.jef.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Controller
public class IndexController {
    @Autowired
    private IUserService userService;
    @ApolloConfig
    private Config config;

    @Value("${username:无法读取到值}")
    private String username;

    @Value("${age:0}")
    private Long age;

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/user/getAllUser")
    public String getAllUser(){
        List<User> userList = userService.getAllUser();
        System.out.println("用户数量=" + userList.size());
        return "用户数量=" + userList.size();
    }


    @GetMapping("/config/getUserName")
    public String getUserName() {
        return config.getProperty("username", "fujie_tu");
    }

    @GetMapping("/config/getUserNameV2")
    public String getUserNameV2() {
        Config config = ConfigService.getAppConfig();
        String key = "username";
        String defaultValue = "fujie_tu";
        return config.getProperty(key, defaultValue);
    }

    /**
     * 启动监听apollo更新
     * @author Jef
     * @date 2021/3/19
     * @return java.lang.String
     */
    @GetMapping("/config/addChangeListener")
    public String getUserNameV3() {
        Config config = ConfigService.getAppConfig();
        config.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                for (String key : changeEvent.changedKeys()) {
                    ConfigChange change = changeEvent.getChange(key);
                    System.out.println(String.format("发现新的变更项 - key: %s, oldValue: %s, newValue: %s, changeType: %s",
                            change.getPropertyName(), change.getOldValue(),
                            change.getNewValue(), change.getChangeType()));
                }
            }
        });
        return "启动监听apollo更新";
    }

    /**
     * 直接获取用户信息
     * @author Jef
     * @date 2021/3/19
     * @return java.lang.String
     */
    @RequestMapping("/config/getUserInfo")
    public String getName() {
        return username + "的年龄" + age;
    }

    @RequestMapping("getUserNoChangeList")
    public Map<String, Object> getUserNoChangeList(){
        System.out.println("微信小程序正在调用。。。");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapOne = new HashMap<String, Object>();
        mapOne.put("name", "张三");
        mapOne.put("phone", "13266860001");
        Map<String, Object> mapTwo = new HashMap<String, Object>();
        mapTwo.put("name", "李四");
        mapTwo.put("phone", "13266860002");
        Map<String, Object> mapThree = new HashMap<String, Object>();
        mapThree.put("name", "王五");
        mapThree.put("phone", "13266860003");
        list.add(mapOne);
        list.add(mapTwo);
        list.add(mapThree);
        resultMap.put("list", list);
        System.out.println("微信小程序调用完成。。。");
        return resultMap;
    }

}