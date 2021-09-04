package com.jef.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 百度工具测试控制器
 *
 * @author Jef
 * @date 2018/9/17 16:21
 */
@Controller
@RequestMapping(value = "/baidu")
public class BaiDuController {

    @RequestMapping(value = "/echart")
    public ModelAndView introduce(ModelAndView mv, Model model) {
        mv.setViewName("baidu/echart");
        return mv;
    }

    /**
     * pie-doughnut 饼图1，全封装，直接传百度饼图需要的数据
     * @param mv
     * @return
     */
    @RequestMapping(value = "/pie-doughnut")
    public ModelAndView pieDoughnut(ModelAndView mv, Model model) {
        String[] projectNameArray = {"泰富", "泰然", "沙河"};
        List<String> projectNameList = Arrays.asList(projectNameArray);
        model.addAttribute("projectNameList", JSON.toJSONString(projectNameList));
        List<Map<String, Object>> showDataList = Lists.newArrayList();
        Map<String, Object> showData = Maps.newHashMap();
        showData.put("value", 1);
        showData.put("name", "泰富");
        showDataList.add(showData);
        Map<String, Object> showData2 = Maps.newHashMap();
        showData2.put("value", 2);
        showData2.put("name", "泰然");
        showDataList.add(showData2);
        Map<String, Object> showData3 = Maps.newHashMap();
        showData3.put("value", 3);
        showData3.put("name", "沙河");
        showDataList.add(showData3);
        model.addAttribute("showDataList", JSON.toJSONString(showDataList));
        mv.setViewName("baidu/pie-doughnut");
        return mv;
    }

    /**
     * pie-doughnut 饼图2，半封装，通过js处理形成百度饼图需要的数据
     * @param mv
     * @return
     */
    @RequestMapping(value = "/pie-doughnut2")
    public ModelAndView pieDoughnut2(ModelAndView mv, Model model) {
        List<Map<String, Object>> showDataList = Lists.newArrayList();
        // 写死一层，实际从数据库中读出形成showDataList
        Map<String, Object> showData = Maps.newHashMap();
        showData.put("totalAmount", 1);
        showData.put("projectName", "泰富");
        showDataList.add(showData);
        Map<String, Object> showData2 = Maps.newHashMap();
        showData2.put("totalAmount", 2);
        showData2.put("projectName", "泰然");
        showDataList.add(showData2);
        Map<String, Object> showData3 = Maps.newHashMap();
        showData3.put("totalAmount", 3);
        showData3.put("projectName", "沙河");
        showDataList.add(showData3);
        model.addAttribute("showDatas", JSON.toJSONString(showDataList));
        mv.setViewName("baidu/pie-doughnut2");
        return mv;
    }

    /**
     * bar-label-rotation 柱状图
     * @param mv
     * @return
     */
    @RequestMapping(value = "/bar-label-rotation")
    public ModelAndView barLabelRotation(ModelAndView mv, Model model) {
        String[] yearNameArray = {"2017年收入", "2018年收入", "同比增长"};
        List<String> yearNameList = Arrays.asList(yearNameArray);
        model.addAttribute("yearNameList", JSON.toJSONString(yearNameList));
        String[] monthNameArray = {"4月", "5月", "6月", "7月", "8月"};
        List<String> monthNameList = Arrays.asList(monthNameArray);
        model.addAttribute("monthNameList", JSON.toJSONString(monthNameList));
        List<Map<String, Object>> showDataList = Lists.newArrayList();
        Map<String, Object> showData = Maps.newHashMap();
        showData.put("name", "2017年收入");
        showData.put("type", "bar");
        showData.put("barGap", 0);
        showData.put("label", "labelOption");
        Integer[] dataNameArray = {320, 332, 301, 334, 390};
        showData.put("data", dataNameArray);
        showDataList.add(showData);
        Map<String, Object> showData2 = Maps.newHashMap();
        showData2.put("name", "2018年收入");
        showData2.put("type", "bar");
        showData2.put("barGap", 0);
        showData2.put("label", "labelOption");
        Integer[] dataNameArray2 = {220, 182, 191, 234, 290};
        showData2.put("data", dataNameArray2);
        showDataList.add(showData2);
        Map<String, Object> showData3 = Maps.newHashMap();
        showData3.put("name", "同比增长");
        showData3.put("type", "bar");
        showData3.put("barGap", 0);
        showData3.put("label", "labelOption");
        Integer[] dataNameArray3 = {1, 2, 3, 4, 5};
        showData3.put("data", dataNameArray3);
        showDataList.add(showData3);
        model.addAttribute("showDataList", JSON.toJSONString(showDataList));
        mv.setViewName("baidu/bar-label-rotation");
        return mv;
    }
}
