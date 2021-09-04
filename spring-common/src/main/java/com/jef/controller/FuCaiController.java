package com.jef.controller;

import com.jef.constant.BasicConstant;
import com.jef.entity.FuCai;
import com.jef.service.IFuCaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Map;

/**
 * 福彩控制器
 *
 * @author Jef
 * @date 2018/9/17 16:21
 */
@Controller
@RequestMapping(value = "/fuCai")
public class FuCaiController {

    @Autowired
    private IFuCaiService fuCaiService;

    @RequestMapping(value = "/fuCai")
    public ModelAndView fuCai(ModelAndView mv, Model model) {
        mv.setViewName("fucai/fucai");
        return mv;
    }

    /**
     * 单个添加
     * @param fuCai
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addInfo", method = RequestMethod.POST)
    public String addInfo(FuCai fuCai) {
        fuCaiService.insert(fuCai);
        return BasicConstant.SUCCESS;
    }

    /**
     * 批量添加
     * @param mv
     * @param model
     * @return
     */
    @RequestMapping(value = "/batchAdd")
    public ModelAndView batchAdd(ModelAndView mv, Model model) {
        mv.setViewName("fucai/batchAdd");
        return mv;
    }

    /**
     * 获取预测信息
     * @param mv
     * @param model
     * @return
     */
    @RequestMapping(value = "/getFuture")
    public ModelAndView getFuture(ModelAndView mv, Model model) {
        Map<String, Integer> redDescMap = fuCaiService.getRedDesc();
        Map<String, Integer> blueDescMap = fuCaiService.getBlueDesc();
        FuCai fuCai = fuCaiService.getByGroup(redDescMap, blueDescMap);
        List<FuCai> fuCaiList = fuCaiService.getAllFuCai();
        model.addAttribute("fuCai", fuCai);
        model.addAttribute("redDescMap", redDescMap);
        model.addAttribute("blueDescMap", blueDescMap);
        model.addAttribute("fuCaiList", fuCaiList);
        mv.setViewName("fucai/futureInfo");
        return mv;
    }

    /**
     * 修改变更次数为0，即未修改
     * @param fuCai
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/changeEditNumZero", method = RequestMethod.POST)
    public String changeEditNumZero(FuCai fuCai) {
        fuCai.setEditNum(0);
        fuCaiService.update(fuCai);
        return BasicConstant.SUCCESS;
    }
}
