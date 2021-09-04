package com.jef.controller;

import com.google.common.collect.Maps;
import com.jef.common.utils.BasicJspUtil;
import com.jef.entity.User;
import com.jef.mq.MessageProduceUtil;
import com.jef.mq.MqConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * activeMQ控制器
 *
 * @author Jef
 * @date 2019/7/3
 */
@Controller
@RequestMapping(value = "/activemq")
public class ActiveMQController {

    private static final Logger logger = LogManager.getLogger(PostController.class);

    /**
     * 其他功能的跳转
     * @param mv
     * @return
     */
    @RequestMapping(value = "activemq")
    public ModelAndView otherIntroduce(ModelAndView mv) {
        mv.setViewName("activemq/activemq");
        return mv;
    }

    /**
     * 方式1，直接用对象接收，推荐使用
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendMessageOne", method = RequestMethod.POST)
    public ModelAndView sendMessageOne(User user) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("log", user.toString());
        MessageProduceUtil.sendQueueMessage(MqConstants.MQ_LOG_SEND, param);
        return BasicJspUtil.getBasicView();
    }
}