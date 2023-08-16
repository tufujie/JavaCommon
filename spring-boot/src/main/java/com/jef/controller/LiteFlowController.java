package com.jef.controller;


import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.core.FlowExecutorHolder;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.property.LiteflowConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author Jef
 * @date 2023/08/16
 */
@Controller
@RequestMapping("/liteFlow")
public class LiteFlowController {
    @Resource
    private FlowExecutor flowExecutor;

    @ResponseBody
    @RequestMapping("/testConfig")
    public String testConfig() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
        return "success";
    }

    @ResponseBody
    @RequestMapping("/checkProcessSeq")
    public String checkProcessSeq(@RequestParam String chainId) {
        String[] chainIdArray = chainId.split(",");
        String result = "";
        for (String chanIdTemp : chainIdArray) {
            LiteflowResponse response = flowExecutor.execute2Resp(chanIdTemp, "arg");
            result += getProcessSeq(response, "验证") + "<br/>";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/loadRuleSource")
    public String loadRuleSource(@RequestParam String fileName, @RequestParam String chainId) {
        LiteflowConfig config = new LiteflowConfig();
        config.setRuleSource("config/" + fileName + ".xml");
        FlowExecutor flowExecutor = FlowExecutorHolder.loadInstance(config);
        LiteflowResponse response = flowExecutor.execute2Resp(chainId, "arg");
        return getProcessSeq(response, "加载文件验证");
    }

    private String getProcessSeq(LiteflowResponse response, String business) {
        StringBuffer sb = new StringBuffer(business + "执行顺序：执行顺序为：");
        int size = response.getExecuteStepQueue().size();
        for (int i = 0; i < size; i++) {
            sb.append(response.getExecuteStepQueue().poll().getNodeId());
            if (i < size - 1) {
                sb.append("->");
            }
        }
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("/balanceLoadRuleSource")
    public String balanceLoadRuleSource(@RequestParam String fileName, @RequestParam String fileNameTwo, @RequestParam String chainId) {
        LiteflowConfig config = new LiteflowConfig();
        // 变更前的规则
        config.setRuleSource("config/" + fileName + ".xml");
        FlowExecutor flowExecutorOne = FlowExecutorHolder.loadInstance(config);
        LiteflowResponse response = flowExecutorOne.execute2Resp(chainId, "arg");
        String before = getProcessSeq(response, "变更前");
        FlowExecutorHolder.clean();
        // 变更后的规则
        LiteflowConfig configTwo = new LiteflowConfig();
        configTwo.setRuleSource("config/" + fileNameTwo + ".xml");
        FlowExecutor flowExecutorTwo = FlowExecutorHolder.loadInstance(configTwo);
        LiteflowResponse responseTwo = flowExecutorTwo.execute2Resp(chainId, "arg");
        String after = getProcessSeq(responseTwo, "变更后");
        return before + "<br/>" + after;
    }

}