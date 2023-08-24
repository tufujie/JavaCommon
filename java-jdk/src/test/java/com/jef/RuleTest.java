package com.jef;

import com.jef.constant.BasicConstant;
import com.jef.entity.User;
import com.jef.rule.impl.ActualRule;
import com.jef.util.JsonUtil;
import com.jef.util.PrintUtil;
import org.junit.Test;

import java.util.Map;

/**
 * @author tufujie
 * @date 2023/8/24
 */
public class RuleTest {

    /**
     * 利用反射执行方法测试
     */
    @Test
    public void testRule() throws Exception {
        // 模拟数据库的真实数据
        User user = new User();
        user.setName(BasicConstant.USER_NAME);
        user.setPhone(BasicConstant.USER_PHONE);
        // 动态配置
        String setParams = "{\"name\": \"Jef\",\"phone\": \"18390220001\"}";
        Map<String, Object> map = JsonUtil.json2map(setParams);
        ActualRule actualRule = new ActualRule();
        String ruleCode = "ruleCodeTest";
        // 可以设置 规则编码=chainId=componentName
        actualRule.pickAndHandle(map, user, ruleCode);
        PrintUtil.printSplitLine();

        user.setName(BasicConstant.USER_NAME_LISI);
        actualRule.pickAndHandle(map, user, ruleCode);
    }

}