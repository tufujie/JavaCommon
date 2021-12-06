package com.jef.controller;

import com.jef.entity.BaseJSONVo;
import com.jef.service.IDubboDemoService;
import com.jef.util.REJSONUtils;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

/**
 * dubbo controller
 *
 * @author Jef
 * @date: 2021/4/15 19:22
 */
@Controller
@RequestMapping(value = "/dubbo")
public class DubboController {

    @DubboReference
    private IDubboDemoService dubboDemoService;

    /**
     * 其他功能的跳转
     *
     * @param mv
     * @return
     */
    @RequestMapping(value = "/dubbo")
    public ModelAndView otherIntroduce(ModelAndView mv) {
        mv.setViewName("dubbo/dubbo");
        return mv;
    }

    @RequestMapping(value = "/registerPermissItem", method = RequestMethod.GET)
    @ResponseBody
    public BaseJSONVo registerPermissItem() throws IOException {
        // 测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-provider.xml");
        context.start();
        System.out.println("dubbo服务已经启动...");
        System.in.read();
        return REJSONUtils.success(true, 0, "操作成功");
    }


    @RequestMapping(value = "/getPermissItem", method = RequestMethod.GET)
    @ResponseBody
    public BaseJSONVo getPermissItem() {
        // 测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/dubbo-consumer.xml");
        context.start();
        System.out.println("消费者1号 开始消费...");
        List<String> permisstionItemList = dubboDemoService.getPermissions(1L);
        System.out.println("消费者1号 获取权限" + permisstionItemList);
        System.out.println("消费者2号 开始消费...");
        IDubboDemoService dubboDemoServiceV2 = context.getBean(IDubboDemoService.class);
        List<String> permisstionItemListV2 = dubboDemoServiceV2.getPermissions(1L);
        System.out.println("消费者2号 获取权限" + permisstionItemListV2);
        System.out.println("消费者2号 结束消费...");
        return REJSONUtils.success(permisstionItemList, 0, "操作成功");
    }


}